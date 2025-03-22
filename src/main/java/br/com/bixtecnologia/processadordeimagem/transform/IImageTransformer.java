package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Image;
import br.com.bixtecnologia.processadordeimagem.dto.ImageDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Image Entity Model para DTO ou vice-versa
 */
public interface IImageTransformer extends IDTOTransformer<Image, ImageDTO> {

}
