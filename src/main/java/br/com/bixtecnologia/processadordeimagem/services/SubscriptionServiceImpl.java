package br.com.bixtecnologia.processadordeimagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.repositories.SubscriptionRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class SubscriptionServiceImpl extends BaseService implements ISubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private MessageService messageService;

	@Override
	public Subscription getSubscriptionByUserId(Long userId) throws BusinessException {
		return Optional
				.of(subscriptionRepository.findByUserIdAndIsActiveAndEndDateIsNull(userId)
						.orElseThrow(() -> new BusinessException(messageService
								.getFormattedMessage("subscription.user.id.not.found", new String[] { userId + "" }))))
				.get();
	}

}
