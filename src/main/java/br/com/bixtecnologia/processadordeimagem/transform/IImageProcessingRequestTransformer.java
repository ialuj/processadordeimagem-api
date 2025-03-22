package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.ImageProcessingRequest;
import br.com.bixtecnologia.processadordeimagem.dto.ImageProcessingRequestDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de ImageProcessingRequest Entity Model para DTO ou vice-versa
 */
public interface IImageProcessingRequestTransformer extends IDTOTransformer<ImageProcessingRequest, ImageProcessingRequestDTO> {

}
