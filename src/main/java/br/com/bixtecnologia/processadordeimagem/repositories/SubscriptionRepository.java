package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	
	@Query("select s from Subscription s where s.userId = :userId and s.isActive = true and s.endDate is NULL")
	public Optional<Subscription> findByUserId(Long userId);

	@Query("select s from Subscription s where s.plan = :plan and s.isActive = true and s.endDate is NULL")
	public List<Subscription> findByPlan(SubscriptionPlan plan);

}
