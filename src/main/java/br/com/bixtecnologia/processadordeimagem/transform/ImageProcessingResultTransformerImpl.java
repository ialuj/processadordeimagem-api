package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingResult;
import br.com.bixtecnologia.processadordeimagem.domain.utils.ProcessingStatus;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingResultDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class ImageProcessingResultTransformerImpl extends DTOTransformerImpl<ImageProcessingResult, ImageProcessingResultDTO> implements IImageProcessingResultTransformer {

	@Override
	public ImageProcessingResultDTO toDTO(ImageProcessingResult source) {
		ImageProcessingResultDTO target = this.transformToDTO(source);
		return target;
	}

	@Override
	public List<ImageProcessingResultDTO> toDTOS(List<ImageProcessingResult> sources) {
		List<ImageProcessingResultDTO> targets = new ArrayList<>();
		for (ImageProcessingResult source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ImageProcessingResultDTO> toDTOS(Set<ImageProcessingResult> sources) {
		Set<ImageProcessingResultDTO> targets = new TreeSet<>();
		for (ImageProcessingResult source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public ImageProcessingResult fromDTO(ImageProcessingResultDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<ImageProcessingResult> fromDTOS(List<ImageProcessingResultDTO> sources) {
		List<ImageProcessingResult> targets = new ArrayList<ImageProcessingResult>();
		for (ImageProcessingResultDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<ImageProcessingResult> fromDTOS(Set<ImageProcessingResultDTO> sources) {
		Set<ImageProcessingResult> targets = new TreeSet<ImageProcessingResult>();
		for (ImageProcessingResultDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public ImageProcessingResult transformFromDTO(ImageProcessingResultDTO source) {
		ImageProcessingResult target = new ImageProcessingResult();
		target.setProcessedFileUrl(source.getProcessedFileUrl());
		target.setResultTime(source.getResultTime());
		target.setStatus(ProcessingStatus.toEnum(source.getStatus()));
		target.setErrorMessage(source.getErrorMessage());
		target.setImageProcessingRequestId(source.getImageProcessingRequestId());
		return target;
	}

	@Override
	public ImageProcessingResultDTO transformToDTO(ImageProcessingResult source) {
		ImageProcessingResultDTO target = new ImageProcessingResultDTO();
		target.setProcessedFileUrl(source.getProcessedFileUrl());
		target.setResultTime(source.getResultTime());
		target.setStatus(source.getStatus().getDescription());
		target.setErrorMessage(source.getErrorMessage());
		target.setImageProcessingRequestId(source.getImageProcessingRequestId());
		return target;
	}

}
