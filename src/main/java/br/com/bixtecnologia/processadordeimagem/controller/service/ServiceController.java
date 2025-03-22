package br.com.bixtecnologia.processadordeimagem.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bixtecnologia.processadordeimagem.controller.base.BaseController;
import br.com.bixtecnologia.processadordeimagem.domain.models.Service;
import br.com.bixtecnologia.processadordeimagem.dto.ServiceDTO;
import br.com.bixtecnologia.processadordeimagem.services.IServiceService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.transform.IServiceTransformer;

@RestController
@RequestMapping("/api/services")
@PreAuthorize("isAuthenticated()")
public class ServiceController extends BaseController {
	
	@Autowired
	private IServiceService serviceService;
	
	@Autowired
	private IServiceTransformer serviceTransformer;
		
	@GetMapping("/all")
	public ResponseEntity<?> getAllServices(Authentication authentication) throws BusinessException {
		try {
		List<Service> services = serviceService.getAllServices();
		List<ServiceDTO> dtos = serviceTransformer.toDTOS(services);
		return ResponseEntity.ok(dtos);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
