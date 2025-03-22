package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingRequest;
import br.com.bixtecnologia.processadordeimagem.domain.utils.Filter;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class ImageProcessingRequestTransformerImpl extends DTOTransformerImpl<ImageProcessingRequest, ImageProcessingRequestDTO> implements IImageProcessingRequestTransformer {

	@Override
	public ImageProcessingRequestDTO toDTO(ImageProcessingRequest source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<ImageProcessingRequestDTO> toDTOS(List<ImageProcessingRequest> sources) {
		List<ImageProcessingRequestDTO> targets = new ArrayList<ImageProcessingRequestDTO>();
		for (ImageProcessingRequest source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ImageProcessingRequestDTO> toDTOS(Set<ImageProcessingRequest> sources) {
		Set<ImageProcessingRequestDTO> targets = new TreeSet<ImageProcessingRequestDTO>();
		for (ImageProcessingRequest source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public ImageProcessingRequest fromDTO(ImageProcessingRequestDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<ImageProcessingRequest> fromDTOS(List<ImageProcessingRequestDTO> sources) {
		List<ImageProcessingRequest> targets = new ArrayList<ImageProcessingRequest>();
		for (ImageProcessingRequestDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ImageProcessingRequest> fromDTOS(Set<ImageProcessingRequestDTO> sources) {
		Set<ImageProcessingRequest> targets = new TreeSet<ImageProcessingRequest>();
		for (ImageProcessingRequestDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public ImageProcessingRequest transformFromDTO(ImageProcessingRequestDTO source) {
		ImageProcessingRequest target = new ImageProcessingRequest();
		target.setCompletionTime(source.getCompletionTime());
		target.setFilter(Filter.toEnum(source.getFilter()));
		target.setRequestTime(source.getRequestTime());
		target.setResizePercentage(source.getResizePercentage());
		target.setImageUrl(source.getImageUrl());
		target.setImageId(source.getImageId());
		return target;
	}

	@Override
	public ImageProcessingRequestDTO transformToDTO(ImageProcessingRequest source) {
		ImageProcessingRequestDTO target = new ImageProcessingRequestDTO();
		target.setCompletionTime(source.getCompletionTime());
		target.setFilter(source.getFilter().getDescription());
		target.setRequestTime(source.getRequestTime());
		target.setResizePercentage(source.getResizePercentage());
		target.setImageUrl(source.getImageUrl());
		target.setImageId(source.getImageId());
		return target;
	}

}
