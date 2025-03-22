package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.repositories.ServiceRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl extends BaseService implements IServiceService {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	private static final Logger logger = Logger.getLogger(ServiceServiceImpl.class.getName());

	@Override
	public List<Service> getAllServices() throws BusinessException {
		try {
		return serviceRepository.findByIsActive(Boolean.TRUE);
		} catch (BusinessException e) {
			ServiceServiceImpl.logger.info(e.getMessage());
			throw new BusinessException("Erro ao pesquisar serviços");
		}
	}

	
}
