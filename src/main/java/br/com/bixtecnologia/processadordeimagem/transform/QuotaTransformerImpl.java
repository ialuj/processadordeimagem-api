package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.dto.QuotaDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class QuotaTransformerImpl extends DTOTransformerImpl<Quota, QuotaDTO> implements IQuotaTransformer {

	@Override
	public QuotaDTO toDTO(Quota source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<QuotaDTO> toDTOS(List<Quota> sources) {
		List<QuotaDTO> targets = new ArrayList<QuotaDTO>();
		for (Quota source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<QuotaDTO> toDTOS(Set<Quota> sources) {
		Set<QuotaDTO> targets = new TreeSet<QuotaDTO>();
		for (Quota source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Quota fromDTO(QuotaDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<Quota> fromDTOS(List<QuotaDTO> sources) {
		List<Quota> targets = new ArrayList<Quota>();
		for (QuotaDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<Quota> fromDTOS(Set<QuotaDTO> sources) {
		Set<Quota> targets = new TreeSet<Quota>();
		for (QuotaDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Quota transformFromDTO(QuotaDTO source) {
		Quota target = new Quota();
		target.setQuota(source.getQuota());
		target.setLastResetDate(source.getLastResetDate());
		target.setSubscriptionId(source.getSubscriptionId());
		return target;
	}

	@Override
	public QuotaDTO transformToDTO(Quota source) {
		QuotaDTO target = new QuotaDTO();
		target.setQuota(source.getQuota());
		target.setLastResetDate(source.getLastResetDate());
		target.setSubscriptionId(source.getSubscriptionId());
		return target;
	}

}
