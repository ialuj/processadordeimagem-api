package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.dto.UserRoleDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de UserRole Entity Model para DTO ou vice-versa
 */
public interface IUserRoleTransformer extends IDTOTransformer<UserRole, UserRoleDTO>  {

}
