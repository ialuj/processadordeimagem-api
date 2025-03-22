package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.repositories.util.RepositoryException;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	@Query("select s from Service s where s.plan =: plan and s.isActive = 1")
	public List<Service> findByPlan(final SubscriptionPlan plan) throws RepositoryException;
	
	@Query("select s from Service s where s.isActive =: isActive")
	public List<Service> findByIsActive(Boolean isActive) throws RepositoryException;

}
