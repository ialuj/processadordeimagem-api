package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IServiceService {
	
	public List<Service> getAllServices() throws BusinessException;

}
