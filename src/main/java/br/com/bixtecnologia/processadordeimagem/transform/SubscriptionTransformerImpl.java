package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.dto.SubscriptionDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class SubscriptionTransformerImpl extends DTOTransformerImpl<Subscription, SubscriptionDTO>
		implements ISubscriptionTransformer {

	@Override
	public SubscriptionDTO toDTO(Subscription source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<SubscriptionDTO> toDTOS(List<Subscription> sources) {
		List<SubscriptionDTO> targets = new ArrayList<SubscriptionDTO>();
		for (Subscription source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<SubscriptionDTO> toDTOS(Set<Subscription> sources) {
		Set<SubscriptionDTO> targets = new TreeSet<SubscriptionDTO>();
		for (Subscription source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Subscription fromDTO(SubscriptionDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<Subscription> fromDTOS(List<SubscriptionDTO> sources) {
		List<Subscription> targets = new ArrayList<Subscription>();
		for (SubscriptionDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<Subscription> fromDTOS(Set<SubscriptionDTO> sources) {
		Set<Subscription> targets = new TreeSet<Subscription>();
		for (SubscriptionDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Subscription transformFromDTO(SubscriptionDTO source) {
		Subscription target = new Subscription();
		super.setValues(source, target);
		target.setPlan(SubscriptionPlan.toEnum(source.getPlan()));
		target.setIsActive(source.getIsActive());
		target.setUserId(source.getUserId());
		return target;
	}

	@Override
	public SubscriptionDTO transformToDTO(Subscription source) {
		SubscriptionDTO target = new SubscriptionDTO();
		super.setValues(source, target);
		target.setPlan(source.getPlan().getDescription());
		target.setIsActive(source.getIsActive());
		target.setUserId(source.getUserId());
		return target;
	}

}
