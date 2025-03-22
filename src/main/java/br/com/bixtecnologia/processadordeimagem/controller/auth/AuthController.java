package br.com.bixtecnologia.processadordeimagem.controller.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bixtecnologia.processadordeimagem.auth.CustomUserDetails;
import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.dto.token.AuthRequest;
import br.com.bixtecnologia.processadordeimagem.dto.token.RefreshTokenDTO;
import br.com.bixtecnologia.processadordeimagem.security.JwtUtil;
import br.com.bixtecnologia.processadordeimagem.services.IRoleService;
import br.com.bixtecnologia.processadordeimagem.services.token.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private IRoleService roleService;

	private final AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Operation(summary = "Login", description = "Recebe o username e password.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o Objeto de Usuario logado com os tokens de acesso, refresh, etc.", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Login feito com sucesso\"}"))),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PostMapping("/login")
	public UserDTO login(@RequestBody AuthRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		UserDTO userDTO = customUserDetails.getUser();

		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userDTO.getEmail());
		claims.put("email", userDTO.getEmail());
		List<Role> roles = new ArrayList<>();
		userDTO.getUserRoles().stream().map(userRole -> roles.add(roleService.getRoleById(userRole.getRoleId())));
		List<String> stringRoles = roles.stream().map(role -> role.getDescription())
				.collect(Collectors.toList());
		Set<String> roleString = new TreeSet<>(stringRoles);
		claims.put("roles", roleString);
		String accessToken = jwtUtil.generateToken(claims, userDTO.getEmail());
		userDTO.setAccessToken(accessToken);

		String refreshTokenString = jwtUtil.generateRefreshToken(claims, userDTO.getEmail());
		userDTO.setRefreshToken(refreshTokenString);

		RefreshTokenDTO refreshToken = new RefreshTokenDTO();
		refreshToken.setUsername(userDTO.getEmail());
		refreshToken.setRefreshToken(refreshTokenString);
		refreshToken.setRevoked(false);
		refreshTokenService.save(refreshToken);

		// AuthResponse response = new AuthResponse(userDTO.getUsername(),
		// userDTO.getPassword(), accessToken, refreshTokenString, roles);
		return userDTO;
	}
}
