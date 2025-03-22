package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.repositories.QuotaRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.utils.DateUtils;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class QuotaServiceImpl extends BaseService implements IQuotaService {

	@Autowired
	private QuotaRepository quotaRepository;

	@Autowired
	private MessageService messageService;

	private static final Logger logger = Logger.getLogger(QuotaServiceImpl.class.getName());

	@Override
	public List<Quota> getQuotaBySubscriptionId(Long subscriptionId) throws BusinessException {
		try {
			return quotaRepository.findBySubscriptionId(subscriptionId);
		} catch (BusinessException e) {
			throw new BusinessException(messageService.getFormattedMessage("quota.search.error", null));
		}
	}

	@Override
	public void resetQuota(Long subscriptionId, User loggedUser) throws BusinessException {
		try {
			List<Quota> quotas = quotaRepository.findBySubscriptionId(subscriptionId);
			Quota quota = quotas.get(0);
			quota.setQuota(10);
			quota.setUpdatedBy(loggedUser.getUuid());
			quota.setUpdateDate(DateUtils.getCurrentLocalDateTime());
			this.quotaRepository.save(quota);
		} catch (BusinessException e) {
			logger.log(Level.SEVERE, messageService.getFormattedMessage("quota.reset.error", null), e);
			throw new BusinessException(messageService.getFormattedMessage("quota.reset.error", null));
		}
	}
}
