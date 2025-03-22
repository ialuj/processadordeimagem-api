package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de User Entity Model para DTO ou vice-versa
 */
public interface IUserTransformer extends IDTOTransformer<User, UserDTO> {

}
