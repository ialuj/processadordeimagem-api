package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.RoleAuthority;
import br.com.bixtecnologia.processadordeimagem.repositories.RoleAuthorityRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class RoleAuthorityServiceImpl extends BaseService implements IRoleAuthorityService {
	
	@Autowired
	private RoleAuthorityRepository roleAuthorityRepository;

	@Override
	public List<RoleAuthority> getRoleAuthoritiesByRoleId(Long roleId) throws BusinessException {
		return roleAuthorityRepository.findByRoleId(roleId);
	}

}
