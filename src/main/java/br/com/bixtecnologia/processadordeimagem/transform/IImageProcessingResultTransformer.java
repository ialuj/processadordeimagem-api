package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingResult;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingResultDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de ImageProcessingResult Entity Model para DTO ou vice-versa
 */
public interface IImageProcessingResultTransformer extends IDTOTransformer<ImageProcessingResult, ImageProcessingResultDTO> {

}
