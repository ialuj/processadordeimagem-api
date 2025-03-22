package br.com.bixtecnologia.processadordeimagem.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 */
@Entity
@Table(name = "user_roles")
public class UserRole extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2116498921868684089L;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", updatable = false, insertable = false)
	private User user;

	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", updatable = false, insertable = false)
	private Role role;

	public UserRole() {
		super();
	}

	public UserRole(User user, Role role) {
		super();
		this.setUser(user);
		this.setRole(role);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if (user != null)
			this.setUserId(user.getId());
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		if (role != null)
			this.setRoleId(role.getId());
	}

}
