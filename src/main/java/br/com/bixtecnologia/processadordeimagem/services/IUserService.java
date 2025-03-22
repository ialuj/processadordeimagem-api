package br.com.bixtecnologia.processadordeimagem.services;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;

/**
 * @author Jose Julai Ritsure
 */
public interface IUserService {

	public User createUser(User user) throws BusinessException;

	public User findUserByEmail(final String email) throws BusinessException;
	
	public User findUserById(final Long id) throws BusinessException;

}
