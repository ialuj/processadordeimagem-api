package br.com.bixtecnologia.processadordeimagem.services.util;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRepository;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class BaseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageService messageService;

	public User getLoggedUser() throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			return userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
					() -> new BusinessException(messageService.getFormattedMessage("user.search.result.email.not.found",
							new String[] { userDetails.getUsername() })));
		}

		throw new BusinessException(messageService.getFormattedMessage("user.logged.not.found", null));
	}

	public User getAdminUser() throws BusinessException {
		try {
			String email = "ritsure.julai.jose@gmail.com";
			return Optional.of(userRepository.findByEmail(email).orElseThrow(
					() -> new BusinessException(messageService.getFormattedMessage("user.email.not.found", new String[] {email}))))
					.get();
		} catch (BusinessException e) {
			throw new BusinessException(messageService.getFormattedMessage("user.search.error", null));
		}
	}

	public String generateUuid() {
		return UUID.randomUUID().toString();
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
