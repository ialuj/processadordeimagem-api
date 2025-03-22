package br.com.bixtecnologia.processadordeimagem.services;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface ISubscriptionService {
	
	public Subscription getSubscriptionByUserId(Long userId) throws BusinessException;

}
