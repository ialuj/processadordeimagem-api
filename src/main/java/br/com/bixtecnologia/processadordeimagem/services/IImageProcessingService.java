package br.com.bixtecnologia.processadordeimagem.services;

import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingResultDTO;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IImageProcessingService {
	
	public ImageProcessingResultDTO processImage(ImageProcessingRequestDTO request) throws BusinessException;

}
