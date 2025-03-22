package br.com.bixtecnologia.processadordeimagem.dto;

/**
 * @author Jose Julai Ritsure
 */
public class UserRoleDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9175253453716972392L;

	private Long userId;

	private UserDTO user;

	private Long roleId;

	private RoleDTO role;	

	public UserRoleDTO() {
		super();
	}

	public UserRoleDTO(UserDTO user, RoleDTO role) {
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
		if (user != null)
			this.setUserId(userId);
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
		if (role != null) this.setRoleId(role.getId());
	}

}
