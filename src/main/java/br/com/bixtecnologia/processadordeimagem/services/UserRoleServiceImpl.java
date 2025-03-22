package br.com.bixtecnologia.processadordeimagem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRoleRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class UserRoleServiceImpl extends BaseService implements IUserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserRole> getUserRolesByUserId(Long userId) throws BusinessException {
		return userRoleRepository.findByUserId(userId);
	}

}
