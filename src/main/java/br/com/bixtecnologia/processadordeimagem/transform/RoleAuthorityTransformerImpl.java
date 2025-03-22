package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.RoleAuthority;
import br.com.bixtecnologia.processadordeimagem.dto.RoleAuthorityDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class RoleAuthorityTransformerImpl extends DTOTransformerImpl<RoleAuthority, RoleAuthorityDTO> implements IRoleAuthorityTransformer {

	@Override
	public RoleAuthorityDTO toDTO(RoleAuthority source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<RoleAuthorityDTO> toDTOS(List<RoleAuthority> sources) {
		List<RoleAuthorityDTO> targets = new ArrayList<>();
		for (RoleAuthority source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<RoleAuthorityDTO> toDTOS(Set<RoleAuthority> sources) {
		Set<RoleAuthorityDTO> targets = new TreeSet<>();
		for (RoleAuthority source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public RoleAuthority fromDTO(RoleAuthorityDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<RoleAuthority> fromDTOS(List<RoleAuthorityDTO> sources) {
		List<RoleAuthority> targets = new ArrayList<RoleAuthority>();
		for (RoleAuthorityDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<RoleAuthority> fromDTOS(Set<RoleAuthorityDTO> sources) {
		Set<RoleAuthority> targets = new TreeSet<RoleAuthority>();
		for (RoleAuthorityDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public RoleAuthority transformFromDTO(RoleAuthorityDTO source) {
		RoleAuthority target = new RoleAuthority();
		target.setAuthorityId(source.getAuthorityId());
		target.setRoleId(source.getRoleId());
		return target;
	}

	@Override
	public RoleAuthorityDTO transformToDTO(RoleAuthority source) {
		RoleAuthorityDTO target = new RoleAuthorityDTO();
		target.setAuthorityId(source.getAuthorityId());
		target.setRoleId(source.getRoleId());
		return target;
	}

}
