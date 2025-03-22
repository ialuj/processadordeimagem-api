package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.dto.token.RefreshTokenDTO;
import br.com.bixtecnologia.processadordeimagem.token.RefreshToken;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class RefreshTokenTransformerImpl extends DTOTransformerImpl<RefreshToken, RefreshTokenDTO> implements IRefreshTokenTransformer {

	@Override
	public RefreshTokenDTO toDTO(RefreshToken source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<RefreshTokenDTO> toDTOS(List<RefreshToken> sources) {
		List<RefreshTokenDTO> targets = new ArrayList<RefreshTokenDTO>();
		for (RefreshToken source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<RefreshTokenDTO> toDTOS(Set<RefreshToken> sources) {
		Set<RefreshTokenDTO> targets = new TreeSet<RefreshTokenDTO>();
		for (RefreshToken source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public RefreshToken fromDTO(RefreshTokenDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<RefreshToken> fromDTOS(List<RefreshTokenDTO> sources) {
		List<RefreshToken> targets = new ArrayList<RefreshToken>();
		for (RefreshTokenDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<RefreshToken> fromDTOS(Set<RefreshTokenDTO> sources) {
		Set<RefreshToken> targets = new TreeSet<RefreshToken>();
		for (RefreshTokenDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public RefreshToken transformFromDTO(RefreshTokenDTO source) {
		RefreshToken target = new RefreshToken();
		target.setId(source.getId());
		target.setDateCreated(source.getDateCreated());
		target.setRefreshToken(source.getRefreshToken());
		target.setRevoked(source.getRevoked());
		target.setUsername(source.getUsername());
		return target;
	}

	@Override
	public RefreshTokenDTO transformToDTO(RefreshToken source) {
		RefreshTokenDTO target = new RefreshTokenDTO();
		target.setId(source.getId());
		target.setDateCreated(source.getDateCreated());
		target.setRefreshToken(source.getRefreshToken());
		target.setRevoked(source.getRevoked());
		target.setUsername(source.getUsername());
		return target;
	}

}
