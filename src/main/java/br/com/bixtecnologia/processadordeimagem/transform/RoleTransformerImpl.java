package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.dto.RoleDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class RoleTransformerImpl extends DTOTransformerImpl<Role, RoleDTO> implements IRoleTransformer {

	@Override
	public RoleDTO toDTO(Role source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<RoleDTO> toDTOS(List<Role> sources) {
		List<RoleDTO> targets = new ArrayList<>();
		for (Role source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<RoleDTO> toDTOS(Set<Role> sources) {
		Set<RoleDTO> targets = new TreeSet<>();
		for (Role source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Role fromDTO(RoleDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<Role> fromDTOS(List<RoleDTO> sources) {
		List<Role> targets = new ArrayList<>();
		for (RoleDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<Role> fromDTOS(Set<RoleDTO> sources) {
		Set<Role> targets = new TreeSet<>();
		for (RoleDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Role transformFromDTO(RoleDTO source) {
		Role target = new Role();
		super.setValues(source, target);
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		return target;
	}

	@Override
	public RoleDTO transformToDTO(Role source) {
		RoleDTO target = new RoleDTO();
		super.setValues(source, target);
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		return target;
	}

}
