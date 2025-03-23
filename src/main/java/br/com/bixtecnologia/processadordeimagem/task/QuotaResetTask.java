package br.com.bixtecnologia.processadordeimagem.task;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.repositories.SubscriptionRepository;
import br.com.bixtecnologia.processadordeimagem.services.IQuotaService;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

@Service
public class QuotaResetTask extends BaseService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private IQuotaService quotaService;

	@Autowired
	private MessageService messageService;

	private static final Logger logger = Logger.getLogger(QuotaResetTask.class.getName());

	@Scheduled(cron = "0 0 0 * * *", zone = "UTC")
	public void resetQuotas() throws BusinessException {
		try {
			logger.info("Iniciado a Task de redefinição das quotas para usuários com Plano Básico");
			List<Subscription> subscriptions = subscriptionRepository.findByPlanAndIsActiveAndEndDateIsNull(SubscriptionPlan.BASIC);
			logger.info("Subscricoes encontradas: " + subscriptions.size());
			User adminUser = this.getAdminUser();
			subscriptions.forEach(subscription -> {
				quotaService.resetQuota(subscription.getId(), adminUser);
			});

		} catch (BusinessException e) {
			logger.log(Level.SEVERE, messageService.getFormattedMessage("quota.task.execution.error", null), e);
			throw new BusinessException(messageService.getFormattedMessage("quota.task.execution.error", null));
		}

	}

}
