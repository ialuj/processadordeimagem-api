package br.com.bixtecnologia.processadordeimagem.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.repositories.RoleAuthorityRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRoleRepository;
import br.com.bixtecnologia.processadordeimagem.transform.IUserRoleTransformer;
import br.com.bixtecnologia.processadordeimagem.transform.IUserTransformer;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;
    
    @Autowired
    private IUserTransformer userTransformer;
    
    @Autowired
    private IUserRoleTransformer userRoleTransformer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuário com e-mail: " + username + " não encontrado." );
        }
        UserDTO user = userTransformer.toDTO(optionalUser.get());
        return new CustomUserDetails(user, userRoleRepository, roleAuthorityRepository, userRoleTransformer);
    }
}

