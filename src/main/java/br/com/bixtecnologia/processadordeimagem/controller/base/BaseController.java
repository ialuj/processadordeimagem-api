package br.com.bixtecnologia.processadordeimagem.controller.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import br.com.bixtecnologia.processadordeimagem.domain.models.BaseModel;
import br.com.bixtecnologia.processadordeimagem.domain.models.Subscription;
import br.com.bixtecnologia.processadordeimagem.domain.models.User;
import br.com.bixtecnologia.processadordeimagem.dto.BaseModelDTO;
import br.com.bixtecnologia.processadordeimagem.dto.UserDTO;
import br.com.bixtecnologia.processadordeimagem.services.ISubscriptionService;
import br.com.bixtecnologia.processadordeimagem.services.IUserService;
import br.com.bixtecnologia.processadordeimagem.transform.IUserTransformer;
import br.com.bixtecnologia.processadordeimagem.utils.Utilities;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public abstract class BaseController {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private IUserTransformer userTransformer;
	
	@Autowired
	private ISubscriptionService subscriptionService;
	
	protected String getAcceptLanguage(HttpServletRequest request) {
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        if (acceptLanguage == null || acceptLanguage.isEmpty()) {
            acceptLanguage = "en";
        }
        Pattern pattern = Pattern.compile("\\b([a-z]{2})");
        Matcher matcher = pattern.matcher(acceptLanguage);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "en";
	}

	protected Locale getLocale(HttpServletRequest request) {
        return new Locale(getAcceptLanguage(request));
    }

    protected  <T extends BaseModel, S extends BaseModelDTO> List<S> listAsDtos(List<T> entities, Class<S> baseEntityDTOClass) {
        if (!Utilities.listHasElements(entities)) return new ArrayList<>();
        try {
            return Utilities.parseList(entities, baseEntityDTOClass);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalStateException("Usuário não autenticado.");
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findUserByEmail(userDetails.getUsername());
            UserDTO userDTO = userTransformer.toDTO(user);
            return userDTO.getId();
        } else if (authentication.getPrincipal() instanceof String) {
            return Long.parseLong(authentication.getPrincipal().toString());
        }

        throw new IllegalStateException("Não foi possível identificar o usuário autenticado.");
    }
    
    protected UserDTO getUserFromAuthentication(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalStateException("Usuário não autenticado.");
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findUserByEmail(userDetails.getUsername());
            user.setSubscriptions(Arrays.asList(getActiveSubscription(user.getId())));
            return userTransformer.toDTO(user);
        } else if (authentication.getPrincipal() instanceof String) {
        	User user = userService.findUserById(Long.parseLong(authentication.getPrincipal().toString()));
        	user.setSubscriptions(Arrays.asList(getActiveSubscription(user.getId())));
            return userTransformer.toDTO(user);
        }

        throw new IllegalStateException("Não foi possível identificar o usuário autenticado.");
    }
    
    private Subscription getActiveSubscription(Long userId) {
    	return subscriptionService.getSubscriptionByUserId(userId);
    }

}
