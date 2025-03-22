package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.repositories.util.RepositoryException;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email) throws RepositoryException;
	
	Optional<User> findByUuid(String uuid) throws RepositoryException;

}
