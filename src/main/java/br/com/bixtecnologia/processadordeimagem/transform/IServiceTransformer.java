package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.dto.ServiceDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Service Entity Model para DTO ou vice-versa
 */
public interface IServiceTransformer extends IDTOTransformer<Service, ServiceDTO> {

}
