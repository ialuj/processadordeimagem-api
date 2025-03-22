package br.com.bixtecnologia.processadordeimagem.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.bixtecnologia.processadordeimagem.domain.models.RoleAuthority;
import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.repositories.RoleAuthorityRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRoleRepository;
import br.com.bixtecnologia.processadordeimagem.transform.IUserRoleTransformer;

public class CustomUserDetails implements UserDetails {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8349898279235635993L;

	private final UserDTO userDTO;

	private final UserRoleRepository userRoleRepository;

	private final RoleAuthorityRepository roleAuthorityRepository;
	
	private final IUserRoleTransformer userRoleTransformer;

	public CustomUserDetails(UserDTO userDTO, UserRoleRepository userRoleRepository,
			RoleAuthorityRepository roleAuthorityRepository, IUserRoleTransformer userRoleTransformer) {
		this.userDTO = userDTO;
		this.userRoleRepository = userRoleRepository;
		this.roleAuthorityRepository = roleAuthorityRepository;
		this.userRoleTransformer = userRoleTransformer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<UserRole> userRoles = userRoleRepository.findByUserId(userDTO.getId());
	    this.userDTO.setUserRoles(userRoleTransformer.toDTOS(userRoles));

	    List<RoleAuthority> roleAuthorities = new ArrayList<>();
	    
	    userRoles.forEach(userRole -> 
	        roleAuthorities.addAll(roleAuthorityRepository.findByRoleId(userRole.getRoleId()))
	    );

	    List<SimpleGrantedAuthority> collect = roleAuthorities.stream()
	            .map(roleAuthority -> new SimpleGrantedAuthority(roleAuthority.getAuthority().getCode()))
	            .collect(Collectors.toList());
		return collect;
	}


	@Override
	public String getPassword() {
		return userDTO.getPassword();
	}

	@Override
	public String getUsername() {
		return userDTO.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserDTO getUser() {
		return userDTO;
	}
}
