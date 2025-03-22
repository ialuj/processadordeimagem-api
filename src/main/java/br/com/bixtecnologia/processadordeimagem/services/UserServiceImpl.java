package br.com.bixtecnologia.processadordeimagem.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bixtecnologia.processadordeimagem.domain.models.Quota;
import br.com.bixtecnologia.processadordeimagem.domain.models.Role;
import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.domain.models.UserRole;
import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import br.com.bixtecnologia.processadordeimagem.repositories.QuotaRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.RoleRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.SubscriptionRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRepository;
import br.com.bixtecnologia.processadordeimagem.repositories.UserRoleRepository;
import br.com.bixtecnologia.processadordeimagem.services.email.EmailSender;
import br.com.bixtecnologia.processadordeimagem.services.util.BaseService;
import br.com.bixtecnologia.processadordeimagem.services.util.BusinessException;
import br.com.bixtecnologia.processadordeimagem.utils.DateUtils;
import br.com.bixtecnologia.processadordeimagem.utils.MessageService;
import br.com.bixtecnologia.processadordeimagem.utils.Utilities;

/**
 * @author Jose Julai Ritsure
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private QuotaRepository quotaRepository;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private EmailSender emailSender;

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	@Transactional
	public User createUser(User user) throws BusinessException {
		try {
			this.validateUserFields(user);
			User adminUser = this.getAdminUser();
			user.setUuid(this.generateUuid());
			user.setCreatedBy(adminUser.getUuid());
			user.setCreationDate(DateUtils.getCurrentLocalDateTime());
			String password = user.getPassword();
			String encryptedPassword = Utilities.encryptPassword(password);
			user.setPassword(encryptedPassword);
			userRepository.save(user);

			Optional<Role> optionalRole = Optional
					.of(roleRepository.findByCode("REGULAR").orElseThrow(() -> new BusinessException(
							messageService.getFormattedMessage("role.code.not.found", new String[] { "REGULAR" }))));

			Role role = optionalRole.get();
			UserRole userRole = new UserRole(user, role);
			userRole.setUuid(this.generateUuid());
			userRole.setCreatedBy(adminUser.getUuid());
			userRole.setCreationDate(DateUtils.getCurrentLocalDateTime());
			userRoleRepository.save(userRole);

			List<Subscription> subscriptions = user.getSubscriptions();
			subscriptions.forEach(subscription -> {
				subscription.setUuid(this.generateUuid());
				subscription.setCreatedBy(adminUser.getUuid());
				subscription.setCreationDate(DateUtils.getCurrentLocalDateTime());
				subscription.setUser(user);
				subscription.setIsActive(Boolean.TRUE);
				subscription.setStartDate(DateUtils.getCurrentLocalDateTime());
			});
			subscriptionRepository.saveAll(subscriptions);
			
			Subscription subscription = subscriptions.get(0);
			if(subscription.getPlan().equals(SubscriptionPlan.BASIC)) {
				Quota quota = new Quota();
				quota.setSubscription(subscription);
				quota.setQuota(10);
				quota.setUuid(this.generateUuid());
				quota.setCreatedBy(adminUser.getUuid());
				quota.setCreationDate(DateUtils.getCurrentLocalDateTime());
				this.quotaRepository.save(quota);
			}

			user.setUserRoles(Arrays.asList(userRole));
			user.setSubscriptions(subscriptions);
			try {
				User userObj = new User();
				userObj.setEmail(user.getEmail());
				userObj.setName(user.getName());
				userObj.setPassword(password);
				emailSender.sendRegistrationEmail(userObj, userObj.getEmail());
			} catch (Exception e) {
				UserServiceImpl.logger.info(e.getMessage());
				throw new BusinessException(
						this.getMessageService().getFormattedMessage("email.send.error", new String[] { user.getEmail() }));
			}
			return user;
		} catch (BusinessException e) {
			UserServiceImpl.logger.info(e.getMessage());
			throw new BusinessException(
					this.getMessageService().getFormattedMessage("user.registration.error", null));
		}
	}

	@Override
	public User findUserByEmail(String email) throws BusinessException {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isEmpty()) {
			throw new BusinessException(this.getMessageService()
					.getFormattedMessage("user.search.result.email.not.found", new String[] { email }));
		}
		return optionalUser.get();
	}

	private void validateUserFields(final User user) {
		if (StringUtils.isBlank(user.getName())) {
			throw new BusinessException(this.getMessageService().getFormattedMessage("user.name.required", null));
		}
		if (StringUtils.isBlank(user.getEmail())) {
			throw new BusinessException(this.getMessageService().getFormattedMessage("user.email.required", null));
		}
		if (StringUtils.isBlank(user.getPassword())) {
			throw new BusinessException(this.getMessageService().getFormattedMessage("user.password.required", null));
		}
		if (user.getSubscriptions() == null || user.getSubscriptions().isEmpty()) {
			throw new BusinessException(
					this.getMessageService().getFormattedMessage("user.subscriptions.required", null));
		}
	}

	@Override
	public User findUserById(Long id) throws BusinessException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new BusinessException(this.getMessageService()
					.getFormattedMessage("user.search.result.id.not.found", new String[] { ""+id }));
		}
		return optionalUser.get();
	}

}
