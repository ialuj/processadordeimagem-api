package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure 
 * Classe que representa um Usuario
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4790162806852508911L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "user")
    private List<Image> processedImages = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<UserRole> userRoles = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Subscription> subscriptions = new ArrayList<>();

	public User() {
		super();
	}

	public User(String name, String email, String password) {
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

	public List<Image> getProcessedImages() {
		return processedImages != null ? processedImages : Collections.emptyList();
	}

	public void setProcessedImages(List<Image> processedImages) {
		this.processedImages = processedImages;
	}

	public List<UserRole> getUserRoles() {
		return userRoles != null ? userRoles : Collections.emptyList();
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions != null ? subscriptions : Collections.emptyList();
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
