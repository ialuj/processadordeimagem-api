package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.dto.ServiceDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class ServiceTransformerImpl extends DTOTransformerImpl<Service, ServiceDTO> implements IServiceTransformer {

	@Override
	public ServiceDTO toDTO(Service source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<ServiceDTO> toDTOS(List<Service> sources) {
		List<ServiceDTO> targets = new ArrayList<>();
		for (Service source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ServiceDTO> toDTOS(Set<Service> sources) {
		Set<ServiceDTO> targets = new TreeSet<>();
		for (Service source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Service fromDTO(ServiceDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<Service> fromDTOS(List<ServiceDTO> sources) {
		List<Service> targets = new ArrayList<>();
		for (ServiceDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<Service> fromDTOS(Set<ServiceDTO> sources) {
		Set<Service> targets = new TreeSet<>();
		for (ServiceDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Service transformFromDTO(ServiceDTO source) {
		Service target = new Service();
		super.setValues(source, target);
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setPlan(source.getPlan());
		target.setActive(source.isActive());
		return target;
	}

	@Override
	public ServiceDTO transformToDTO(Service source) {
		ServiceDTO target = new ServiceDTO();
		super.setValues(source, target);
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setPlan(source.getPlan().toString());
		target.setActive(source.isActive());
		return target;
	}

}
