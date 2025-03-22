package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.repositories.util.RepositoryException;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	public List<UserRole> findByUserId(Long userId) throws RepositoryException;

}
