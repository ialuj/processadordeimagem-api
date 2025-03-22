package br.com.bixtecnologia.processadordeimagem.services;

import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IRoleService {
	
	public Role getRoleById(Long roleId) throws BusinessException;

}
