package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IQuotaService {
	
	public List<Quota> getQuotaBySubscriptionId(Long subscriptionId) throws BusinessException;
	
	public void resetQuota(Long subscriptionId, User loggedUser) throws BusinessException;

}
