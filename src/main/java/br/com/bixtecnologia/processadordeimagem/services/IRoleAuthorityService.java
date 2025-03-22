package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import br.com.bixtecnologia.processadordeimagem.domain.models.RoleAuthority;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IRoleAuthorityService {
	
	public List<RoleAuthority> getRoleAuthoritiesByRoleId(Long roleId) throws BusinessException;

}
