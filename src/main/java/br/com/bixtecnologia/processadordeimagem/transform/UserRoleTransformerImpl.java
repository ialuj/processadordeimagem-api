package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.dto.UserRoleDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class UserRoleTransformerImpl extends DTOTransformerImpl<UserRole, UserRoleDTO> implements IUserRoleTransformer {
	
	@Autowired
	private IRoleTransformer roleTransformer;

	@Override
	public UserRoleDTO toDTO(UserRole source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<UserRoleDTO> toDTOS(List<UserRole> sources) {
		List<UserRoleDTO> targets = new ArrayList<UserRoleDTO>();
		for (UserRole source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<UserRoleDTO> toDTOS(Set<UserRole> sources) {
		Set<UserRoleDTO> targets = new TreeSet<UserRoleDTO>();
		for (UserRole source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public UserRole fromDTO(UserRoleDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<UserRole> fromDTOS(List<UserRoleDTO> sources) {
		List<UserRole> targets = new ArrayList<UserRole>();
		for (UserRoleDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<UserRole> fromDTOS(Set<UserRoleDTO> sources) {
		Set<UserRole> targets = new TreeSet<UserRole>();
		for (UserRoleDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public UserRole transformFromDTO(UserRoleDTO source) {
		UserRole target = new UserRole();
		super.setValues(source, target);
		target.setRoleId(source.getRoleId());
		target.setUserId(source.getUserId());
		if(source.getRole() != null) this.roleTransformer.fromDTO(source.getRole());
		return target;
	}

	@Override
	public UserRoleDTO transformToDTO(UserRole source) {
		UserRoleDTO target = new UserRoleDTO();
		super.setValues(source, target);
		target.setRoleId(source.getRoleId());
		target.setUserId(source.getUserId());
		if(source.getRole() != null) this.roleTransformer.toDTO(source.getRole());
		return target;
	}

}
