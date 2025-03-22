package br.com.bixtecnologia.processadordeimagem.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Jose Julai Ritsure
 */
public class UserDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1072143594144962259L;
	
	@NotBlank(message = "É obrigatório preencher o nome completo do usuário")
	private String name;
	
	@NotBlank(message = "É obrigatório preencher o e-mail do usuário")
	private String email;
	
	@NotBlank(message = "É obrigatório preencher a senha do usuário")
	private String password;
	
	private String accessToken;
	
	private String refreshToken;
	
	private List<ImageDTO> processedImages;
	
	private List<UserRoleDTO> userRoles;
	
	@NotNull(message = "É obrigatório indicar a subscrição")
	private List<SubscriptionDTO> subscriptions;

	public UserDTO() {
		super();
	}

	public UserDTO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<ImageDTO> getProcessedImages() {
		return processedImages;
	}

	public void setProcessedImages(List<ImageDTO> processedImages) {
		this.processedImages = processedImages;
	}

	public List<UserRoleDTO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleDTO> userRoles) {
		this.userRoles = userRoles;
	}

	public List<SubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
