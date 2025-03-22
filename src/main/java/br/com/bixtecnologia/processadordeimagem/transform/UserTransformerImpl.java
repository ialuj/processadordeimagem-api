package br.com.bixtecnologia.processadordeimagem.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bixtecnologia.processadordeimagem.domain.models.Image;
import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.dto.ImageDTO;
import br.com.bixtecnologia.processadordeimagem.dto.SubscriptionDTO;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.dto.UserRoleDTO;
import br.com.bixtecnologia.processadordeimagem.transform.base.DTOTransformerImpl;

/**
 * @author Jose Julai Ritsure
 */
@Component
public class UserTransformerImpl extends DTOTransformerImpl<User, UserDTO> implements IUserTransformer {
	
	@Autowired
	private ISubscriptionTransformer subscriptionTransformer;
	
	@Autowired
	private IImageTransformer imageTransformer;
	
	@Autowired
	private IUserRoleTransformer userRoleTransformer;

	@Override
	public UserDTO toDTO(User source) {
		if (source == null) {
	        return null;
	    }
		return this.transformToDTO(source);
	}

	@Override
	public List<UserDTO> toDTOS(List<User> sources) {
		List<UserDTO> targets = new ArrayList<>();
		for (User source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public Set<UserDTO> toDTOS(Set<User> sources) {
		Set<UserDTO> targets = new TreeSet<>();
		for (User source : sources) {
			targets.add(this.transformToDTO(source));
		}
		return targets;
	}

	@Override
	public User fromDTO(UserDTO source) {
		return this.transformFromDTO(source);
	}

	@Override
	public List<User> fromDTOS(List<UserDTO> sources) {
		List<User> targets = new ArrayList<>();
		for (UserDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public Set<User> fromDTOS(Set<UserDTO> sources) {
		Set<User> targets = new TreeSet<>();
		for (UserDTO source : sources) {
			targets.add(this.transformFromDTO(source));
		}
		return targets;
	}

	@Override
	public User transformFromDTO(UserDTO source) {
		User target = new User();
		super.setValues(source, target);
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setPassword(source.getPassword());
		if(source.getSubscriptions() != null && !source.getSubscriptions().isEmpty()) {
			List<Subscription> subscriptions = subscriptionTransformer.fromDTOS(source.getSubscriptions());
			target.setSubscriptions(subscriptions);
		}
		if(source.getProcessedImages() != null && !source.getProcessedImages().isEmpty()) {
			List<Image> processedImages = imageTransformer.fromDTOS(source.getProcessedImages());
			target.setProcessedImages(processedImages);
		}
		if(source.getUserRoles() != null && !source.getUserRoles().isEmpty()) {
			List<UserRole> userRoles = userRoleTransformer.fromDTOS(source.getUserRoles());
			target.setUserRoles(userRoles);
		}
		return target;
	}

	@Override
	public UserDTO transformToDTO(User source) {
		UserDTO target = new UserDTO();
		super.setValues(source, target);
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setPassword(source.getPassword());
		if(source.getSubscriptions() != null && !source.getSubscriptions().isEmpty()) {
			List<SubscriptionDTO> subscriptions = subscriptionTransformer.toDTOS(source.getSubscriptions());
			target.setSubscriptions(subscriptions);
		}
		if(source.getProcessedImages() != null && !source.getProcessedImages().isEmpty()) {
			List<ImageDTO> processedImages = imageTransformer.toDTOS(source.getProcessedImages());
			target.setProcessedImages(processedImages);
		}
		if(source.getUserRoles() != null && !source.getUserRoles().isEmpty()) {
			List<UserRoleDTO> userRoles = userRoleTransformer.toDTOS(source.getUserRoles());
			target.setUserRoles(userRoles);
		}
		return target;
	}

}
