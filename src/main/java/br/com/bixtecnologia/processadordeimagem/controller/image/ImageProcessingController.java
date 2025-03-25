package br.com.bixtecnologia.processadordeimagem.controller.image;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bixtecnologia.processadordeimagem.controller.base.BaseController;
import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.dto.SubscriptionDTO;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.services.IQuotaService;
import br.com.bixtecnologia.processadordeimagem.services.email.EmailSender;
import br.com.bixtecnologia.processadordeimagem.services.quee.ImageProcessingProducer;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

@RestController
@RequestMapping("/api/images")
public class ImageProcessingController extends BaseController {

	@Autowired
	private ImageProcessingProducer imageProcessingProducer;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private MessageService messageService;

	@Autowired
	private IQuotaService quotaService;

	@PostMapping("/process")
	@PreAuthorize("hasAuthority('Regular')")
	public ResponseEntity<String> processImage(@RequestBody ImageProcessingRequestDTO request,
			Authentication authentication) {

		UserDTO loggedUser = this.getUserFromAuthentication(authentication);
		SubscriptionDTO subscription = loggedUser.getSubscriptions().get(0);

		if (SubscriptionPlan.BASIC.toString().equalsIgnoreCase(subscription.getPlan())) {
			List<Quota> quotas = quotaService.getQuotaBySubscriptionId(subscription.getId());

			if (quotas == null || quotas.isEmpty()) {
				return ResponseEntity.badRequest().body(messageService.getFormattedMessage("user.subscription.expired",
						new String[] { loggedUser.getName() }));
			}

			if (quotas.get(0).getQuota() < 1) {
				return ResponseEntity.badRequest().body(messageService.getFormattedMessage("user.quota.maximum.expired",
						new String[] { loggedUser.getName() }));
			}
		}

		if (request.getImageUrl() == null || request.getImageUrl().isEmpty()) {
			return ResponseEntity.badRequest().body("A URL da imagem não foi fornecida.");
		}

		UserDTO userDTO = this.getUserFromAuthentication(authentication);
		request.setCreatedBy(userDTO.getUuid());

		try {
			emailSender.sendProcessingRequestEmail(userDTO.getName(), userDTO.getEmail());
		} catch (Exception e) {
			String errorMessage = messageService.getFormattedMessage("email.send.error",
					new String[] { userDTO.getEmail() });
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}

		imageProcessingProducer.sendMessage(request);
		return ResponseEntity.ok("Imagem enviada para processamento: " + request.getImageUrl());
	}

}
