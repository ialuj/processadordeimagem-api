package br.com.bixtecnologia.processadordeimagem.controller.image;

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
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.services.email.EmailSender;
import br.com.bixtecnologia.processadordeimagem.services.quee.ImageProcessingProducer;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

@RestController
@RequestMapping("/api/images")
@PreAuthorize("isAuthenticated()")
public class ImageProcessingController extends BaseController {

	@Autowired
	private ImageProcessingProducer imageProcessingProducer;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private MessageService messageService;

	@PostMapping("/process")
	public ResponseEntity<String> processImage(@RequestBody ImageProcessingRequestDTO request,
			Authentication authentication) {
		UserDTO userDTO = this.getUserFromAuthentication(authentication);
		request.setCreatedBy(userDTO.getUuid());
		try {
			emailSender.sendProcessingRequestEmail(userDTO.getName(), userDTO.getEmail());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(messageService.getFormattedMessage("email.send.error", new String[] { userDTO.getEmail() }));
		}
		imageProcessingProducer.sendMessage(request);
		return ResponseEntity.ok("Imagem enviada para processamento: " + request.getImageUrl());
	}
}
