package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IUserRoleService {
	
	public List<UserRole> getUserRolesByUserId(Long userId) throws BusinessException;

}
