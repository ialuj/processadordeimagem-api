package br.com.bixtecnologia.processadordeimagem.repositories.token;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bixtecnologia.processadordeimagem.token.RefreshToken;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(@NotNull @NotBlank String refreshToken);

    Optional<RefreshToken> findByUsernameAndRevoked(String userName, Boolean revoked);

}
