package br.com.bixtecnologia.processadordeimagem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.domain.models.RoleAuthority;
import br.com.bixtecnologia.processadordeimagem.repositories.util.RepositoryException;

/**
 * @author Jose Julai Ritsure
 */
@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long> {
	
	public List<RoleAuthority> findByRoleId(Long roleId) throws RepositoryException;

}
