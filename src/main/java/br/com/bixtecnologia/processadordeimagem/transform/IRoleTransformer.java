package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.dto.RoleDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Role Entity Model para DTO ou vice-versa
 */
public interface IRoleTransformer extends IDTOTransformer<Role, RoleDTO> {

}
