package br.com.bixtecnologia.processadordeimagem.transform;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.dto.SubscriptionDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.IDTOTransformer;

/**
 * Jose Julai Ritsure 
 * Classe para transformacao de Subscription Entity Model para DTO ou vice-versa
 */
public interface ISubscriptionTransformer extends IDTOTransformer<Subscription, SubscriptionDTO> {

}
