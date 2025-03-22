package br.com.bixtecnologia.processadordeimagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.repositories.RoleRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class RoleServiceImpl extends BaseService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageService messageService;

	@Override
	public Role getRoleById(Long roleId) throws BusinessException {
		return Optional
				.of(roleRepository.findById(roleId)
						.orElseThrow(() -> new BusinessException(
								messageService.getFormattedMessage("role.id.not.found", new String[] { roleId + "" }))))
				.get();
	}

}
