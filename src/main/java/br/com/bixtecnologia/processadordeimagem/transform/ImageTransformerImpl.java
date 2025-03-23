package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Image;
import br.com.bixtecnologia.processadordeimagem.domain.utils.Filter;
import br.com.bixtecnologia.processadordeimagem.domain.utils.ProcessingStatus;
import br.com.bixtecnologia.processadordeimagem.dto.ImageDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class ImageTransformerImpl extends DTOTransformerImpl<Image, ImageDTO> implements IImageTransformer {

	@Override
	public ImageDTO toDTO(Image source) {
		return this.transformToDTO(source);
	}

	@Override
	public List<ImageDTO> toDTOS(List<Image> sources) {
		List<ImageDTO> targets = new ArrayList<ImageDTO>();
		for (Image source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ImageDTO> toDTOS(Set<Image> sources) {
		Set<ImageDTO> targets = new TreeSet<ImageDTO>();
		for (Image source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Image fromDTO(ImageDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<Image> fromDTOS(List<ImageDTO> sources) {
		List<Image> targets = new ArrayList<Image>();
		for (ImageDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<Image> fromDTOS(Set<ImageDTO> sources) {
		Set<Image> targets = new TreeSet<Image>();
		for (ImageDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Image transformFromDTO(ImageDTO source) {
		Image target = new Image();
		target.setFilePath(source.getFilePath());
		target.setHeight(source.getHeight());
		target.setWidth(source.getWidth());
		target.setOriginalFileName(source.getOriginalFileName());
		target.setStatus(ProcessingStatus.toEnum(source.getStatus()));
		if (!StringUtils.isBlank(source.getFilter()))
			target.setFilter(Filter.toEnum(source.getFilter()));
		target.setUserId(source.getUserId());
		return target;
	}

	@Override
	public ImageDTO transformToDTO(Image source) {
		ImageDTO target = new ImageDTO();
		target.setFilePath(source.getFilePath());
		target.setHeight(source.getHeight());
		target.setWidth(source.getWidth());
		target.setOriginalFileName(source.getOriginalFileName());
		target.setStatus(source.getStatus().getDescription());
		if (source.getFilter() != null)
			target.setFilter(source.getFilter().getDescription());
		target.setUserId(source.getUserId());
		return target;
	}

}
