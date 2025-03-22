package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Authority;
import br.com.bixtecnologia.processadordeimagem.dto.AuthorityDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Authority Entity Model para DTO ou vice-versa
 */
public interface IAuthorityTransformer extends IDTOTransformer<Authority, AuthorityDTO> {

}
