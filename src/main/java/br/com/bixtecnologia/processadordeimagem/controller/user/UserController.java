package br.com.bixtecnologia.processadordeimagem.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bixtecnologia.processadordeimagem.controller.base.BaseController;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.services.IUserService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.transform.IUserTransformer;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserTransformer userTransformer;
		
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) throws BusinessException {
		try {
			User user = userTransformer.fromDTO(userDTO);
			UserDTO response = userTransformer.toDTO(userService.createUser(user));
			return ResponseEntity.ok(response);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	

}
