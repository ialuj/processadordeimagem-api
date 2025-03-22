package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
	
	public List<Quota> findBySubscriptionId(Long subscriptionId);

}
