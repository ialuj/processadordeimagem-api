package br.com.bixtecnologia.processadordeimagem.dto;

/**
 * @author Jose Julai Ritsure
 */
public class RoleAuthorityDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6414966911637073898L;
	
	private Long authorityId;
	
	private AuthorityDTO authority;
	
	private Long roleId;
	
	private RoleDTO role;

	public RoleAuthorityDTO() {
		super();
	}

	public RoleAuthorityDTO(AuthorityDTO authority, RoleDTO role) {
		super();
		this.setAuthority(authority);
		this.setRole(role);
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
		if(authority != null) this.setAuthorityId(authority.getId());
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
		if(role != null) this.setRoleId(role.getId());
	}

}
