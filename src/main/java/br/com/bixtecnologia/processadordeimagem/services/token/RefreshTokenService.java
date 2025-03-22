package br.com.bixtecnologia.processadordeimagem.services.token;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bixtecnologia.processadordeimagem.dto.token.RefreshTokenDTO;
import br.com.bixtecnologia.processadordeimagem.repositories.token.RefreshTokenRepository;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.token.RefreshToken;
import br.com.bixtecnologia.processadordeimagem.transform.IRefreshTokenTransformer;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;

@Service
public class RefreshTokenService extends BaseService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private IRefreshTokenTransformer refreshTokenTransformer;

	@Transactional
	public RefreshTokenDTO save(RefreshTokenDTO refreshTokenDTO) {
		RefreshToken refreshToken = refreshTokenTransformer.fromDTO(refreshTokenDTO);
		refreshToken.setDateCreated(Instant.now());
		refreshTokenRepository.save(refreshToken);
		return refreshTokenTransformer.toDTO(refreshToken);
	}

	public RefreshTokenDTO findByRefreshToken(String refreshToken) {
		Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
		if (optionalRefreshToken.isEmpty()) {
			throw new BusinessException(this.getMessageService().getFormattedMessage("refresh.token.not.found",
					new String[] { refreshToken }));
		}
		RefreshToken refreshTokenValue = optionalRefreshToken.get();
		return refreshTokenTransformer.toDTO(refreshTokenValue);
	}

}
