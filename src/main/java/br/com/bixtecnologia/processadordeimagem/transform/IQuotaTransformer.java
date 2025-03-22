package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.dto.QuotaDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Quota Entity Model para DTO ou vice-versa
 */
public interface IQuotaTransformer extends IDTOTransformer<Quota, QuotaDTO> {

}
