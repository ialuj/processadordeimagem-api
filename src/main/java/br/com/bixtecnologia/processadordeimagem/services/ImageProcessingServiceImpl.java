package br.com.bixtecnologia.processadordeimagem.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bixtecnologia.processadordeimagem.domain.models.Image;
import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingRequest;
import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingResult;
import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.domain.utils.ProcessingStatus;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.dto.ImageDTO;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingResultDTO;
import br.com.bixtecnologia.processadordeimagem.repositories.ImageProcessingRequestRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.ImageProcessingResultRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.ImageRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.SubscriptionRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRepository;
import br.com.bixtecnologia.processadordeimagem.services.email.EmailSender;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.transform.IImageProcessingRequestTransformer;
import br.com.bixtecnologia.processadordeimagem.transform.IImageProcessingResultTransformer;
import br.com.bixtecnologia.processadordeimagem.transform.IImageTransformer;
import br.com.bixtecnologia.processadordeimagem.utils.DateUtils;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

/**
 * @author Jose Julai Ritsure
 * 
 *         Classe de servico responsavel pelo processamento da imagem
 * 
 */
@Service
public class ImageProcessingServiceImpl extends BaseService implements IImageProcessingService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImageProcessingRequestRepository imageProcessingRequestRepository;

	@Autowired
	private ImageProcessingResultRepository imageProcessingResultRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private IQuotaService quotaService;

	@Autowired
	private IImageTransformer imageTransformer;

	@Autowired
	private IImageProcessingRequestTransformer imageProcessingRequestTransformer;

	@Autowired
	private IImageProcessingResultTransformer imageProcessingResultTransformer;

	@Autowired
	private MessageService messageService;

	@Autowired
	private EmailSender emailSender;

	private static final Logger logger = Logger.getLogger(ImageProcessingServiceImpl.class.getName());

	@Override
	@Transactional
	public ImageProcessingResultDTO processImage(ImageProcessingRequestDTO request) throws BusinessException {
		try {
			User loggedUser = Optional.of(this.userRepository.findByUuid(request.getCreatedBy())
					.orElseThrow(() -> new BusinessException(messageService.getFormattedMessage("user.uuid.not.found",
							new String[] { request.getCreatedBy() }))))
					.get();

			Subscription subscription = getAndValidateSubscription(loggedUser);
			if (SubscriptionPlan.BASIC.equals(subscription.getPlan())) {
				List<Quota> quotas = quotaService.getQuotaBySubscriptionId(subscription.getId());
				Quota quota = quotas.get(0);
				if (quota.getQuota() < 1) {
					throw new BusinessException(messageService.getFormattedMessage("user.quota.maximum..expired",
							new String[] { loggedUser.getName() }));
				}
				int quotaNumber = quota.getQuota();
				quota.setQuota(quotaNumber - 1);
			}

			this.validateImageProcessingRequest(request);

			ImageProcessingRequest imageProcessingRequest = imageProcessingRequestTransformer.fromDTO(request);
			Image image = imageTransformer.fromDTO(request.getImage());

			image.setUser(getAdminUser());
			image.setUuid(this.generateUuid());
			image.setCreatedBy(loggedUser.getUuid());
			image.setCreationDate(DateUtils.getCurrentLocalDateTime());
			image.setStatus(ProcessingStatus.PENDING);
			this.imageRepository.save(image);

			imageProcessingRequest.setImage(image);
			imageProcessingRequest.setUuid(this.generateUuid());
			imageProcessingRequest.setRequestTime(DateUtils.getCurrentLocalDateTime());
			imageProcessingRequest.setCreatedBy(loggedUser.getUuid());
			imageProcessingRequest.setCreationDate(DateUtils.getCurrentLocalDateTime());
			this.imageProcessingRequestRepository.save(imageProcessingRequest);

			ImageProcessingResult imageProcessingResult = new ImageProcessingResult();
			imageProcessingResult.setImageProcessingRequest(imageProcessingRequest);
			imageProcessingResult.setStatus(ProcessingStatus.PENDING);
			imageProcessingResult.setUuid(this.generateUuid());
			imageProcessingResult.setCreatedBy(loggedUser.getUuid());
			imageProcessingResult.setCreationDate(DateUtils.getCurrentLocalDateTime());
			this.imageProcessingResultRepository.save(imageProcessingResult);

			File file = processImageFile(request);
			imageProcessingRequest.setCompletionTime(DateUtils.getCurrentLocalDateTime());
			imageProcessingRequest.setUpdatedBy(this.generateUuid());
			imageProcessingRequest.setUpdateDate(DateUtils.getCurrentLocalDateTime());
			this.imageProcessingRequestRepository.save(imageProcessingRequest);

			image.setUpdatedBy(this.generateUuid());
			image.setUpdateDate(DateUtils.getCurrentLocalDateTime());
			image.setStatus(ProcessingStatus.COMPLETED);
			this.imageRepository.save(image);

			imageProcessingResult.setResultTime(DateUtils.getCurrentLocalDateTime());
			imageProcessingResult.setUpdatedBy(this.generateUuid());
			imageProcessingResult.setUpdateDate(DateUtils.getCurrentLocalDateTime());
			imageProcessingResult.setProcessedFileUrl(file.getPath());
			imageProcessingResult.setStatus(ProcessingStatus.COMPLETED);
			this.imageProcessingResultRepository.save(imageProcessingResult);

			ImageProcessingRequestDTO imageProcessingRequestDTO = imageProcessingRequestTransformer
					.toDTO(imageProcessingRequest);
			ImageDTO imageDTO = imageTransformer.toDTO(image);
			imageProcessingRequestDTO.setImage(imageDTO);
			ImageProcessingResultDTO imageProcessingResultDTO = imageProcessingResultTransformer
					.toDTO(imageProcessingResult);

			try {
				emailSender.sendProcessingResultEmail(loggedUser.getName(), imageProcessingResult.getProcessedFileUrl(),
						loggedUser.getEmail());
			} catch (Exception e) {
				logger.info(e.getMessage());
				throw new BusinessException(this.getMessageService().getFormattedMessage("email.send.error",
						new String[] { loggedUser.getEmail() }));
			}

			if (SubscriptionPlan.BASIC.equals(subscription.getPlan())) {
				List<Quota> quotas = quotaService.getQuotaBySubscriptionId(subscription.getId());
				Quota quota = quotas.get(0);
				if (quota.getQuota() < 1) {
					throw new BusinessException(messageService.getFormattedMessage("user.quota.maximum..expired",
							new String[] { loggedUser.getName() }));
				}
				quotaService.resetQuota(subscription.getId(), loggedUser);
			}

			return imageProcessingResultDTO;
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, errorMessage(), e);
			throw new BusinessException(errorMessage());
		}

	}

	private String errorMessage() {
		return messageService.getFormattedMessage("image.request.process.error", null);
	}

	private File processImageFile(ImageProcessingRequestDTO request) throws BusinessException {
		try {
			String uploadDir = System.getenv("UPLOAD_DIR") != null ? System.getenv("UPLOAD_DIR")
					: "/app/uploads/";
            logger.info("Diretório de upload: " + uploadDir);

			String imagePath = request.getImageUrl();
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
			    imagePath = imagePath.replace("/app/uploads", "./uploads");
			    uploadDir = uploadDir.replace("/app/uploads", "./uploads");
			}

			File imageFile = new File(imagePath);

			if (!imageFile.exists()) {
				throw new BusinessException("A imagem não foi encontrada: " + request.getImageUrl());
			}

			BufferedImage originalImage = ImageIO.read(imageFile);

			int newWidth = (int) (originalImage.getWidth() * (request.getResizePercentage() / 100.0));
			int newHeight = (int) (originalImage.getHeight() * (request.getResizePercentage() / 100.0));
			BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
			g.dispose();

			if ("Grayscale".equalsIgnoreCase(request.getFilter())) {
				resizedImage = applyGrayscaleFilter(resizedImage);
			} else if ("Sepia".equalsIgnoreCase(request.getFilter())) {
				resizedImage = applySepiaFilter(resizedImage);
			}

			File output = new File(uploadDir, "processed_image_" + DateUtils.getCurrentDate().getTime() + ".jpg");
			ImageIO.write(resizedImage, "jpg", output);

			return output;
		} catch (IOException e) {
			logger.log(Level.SEVERE, errorMessage(), e);
			throw new BusinessException(errorMessage());
		}
	}

	private BufferedImage applyGrayscaleFilter(BufferedImage image) {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color color = new Color(image.getRGB(x, y));
				int gray = (int) (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11);
				image.setRGB(x, y, new Color(gray, gray, gray).getRGB());
			}
		}
		return image;
	}

	private BufferedImage applySepiaFilter(BufferedImage image) {
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color color = new Color(image.getRGB(x, y));
				int tr = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
				int tg = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
				int tb = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

				tr = Math.min(255, tr);
				tg = Math.min(255, tg);
				tb = Math.min(255, tb);

				image.setRGB(x, y, new Color(tr, tg, tb).getRGB());
			}
		}
		return image;
	}

	private Subscription getAndValidateSubscription(User user) throws BusinessException {
		return Optional
				.of(this.subscriptionRepository
						.findByUserIdAndIsActiveTrueAndEndDateAfterOrEndDateIsNull(user.getId(), LocalDateTime.now())
						.orElseThrow(() -> new BusinessException(messageService
								.getFormattedMessage("user.subscription.expired", new String[] { user.getName() }))))
				.get();
	}

	private void validateImageProcessingRequest(ImageProcessingRequestDTO request) throws BusinessException {
		if (request.getImage() == null) {
			throw new BusinessException(messageService.getFormattedMessage("image.request.image.not.found", null));
		}
		if (request.getResizePercentage() == null && StringUtils.isBlank(request.getFilter())) {
			throw new BusinessException(
					messageService.getFormattedMessage("image.request.filter.or.resizePercentage.not.found", null));
		}
		if (StringUtils.isBlank(request.getImageUrl())) {
			throw new BusinessException(messageService.getFormattedMessage("image.request.url.not.found", null));
		}
	}

}
