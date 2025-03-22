package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.Role;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Optional<Role> findByCode(String code);

}
