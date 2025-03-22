package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Authority;
import br.com.bixtecnologia.processadordeimagem.dto.AuthorityDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class AuthorityTransformerImpl extends DTOTransformerImpl<Authority, AuthorityDTO> implements IAuthorityTransformer {

	@Override
	public AuthorityDTO toDTO(Authority source) {
		return super.toDTO(source);
	}

	@Override
	public List<AuthorityDTO> toDTOS(List<Authority> sources) {
		return super.toDTOS(sources);
	}

	@Override
	public Set<AuthorityDTO> toDTOS(Set<Authority> sources) {
		return super.toDTOS(sources);
	}

	@Override
	public Authority fromDTO(AuthorityDTO source) {
		return super.fromDTO(source);
	}

	@Override
	public List<Authority> fromDTOS(List<AuthorityDTO> sources) {
		return super.fromDTOS(sources);
	}

	@Override
	public Set<Authority> fromDTOS(Set<AuthorityDTO> sources) {
		return super.fromDTOS(sources);
	}

	@Override
	public Authority transformFromDTO(AuthorityDTO source) {
		Authority target = new Authority();
		super.setValues(source, target);
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		return target;
	}

	@Override
	public AuthorityDTO transformToDTO(Authority source) {
		AuthorityDTO target = new AuthorityDTO();
		super.setValues(source, target);
		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		return target;
	}

}
