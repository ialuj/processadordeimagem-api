package br.com.bixtecnologia.processadordeimagem.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 * Classe que representa a associacao entre papel e permissao
 */
@Entity
@Table(name = "role_authorities")
public class RoleAuthority extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6026793013438268208L;
	
	@Column(name = "AUTHORITY_ID", nullable = false)
	private Long authorityId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTHORITY_ID", updatable = false, insertable = false)
	private Authority authority;
	
	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", updatable = false, insertable = false)
	private Role role;

	public RoleAuthority() {
		super();
	}

	public RoleAuthority(Authority authority, Role role) {
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
		if(authority != null) this.setAuthorityId(authority.getId());
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
		if(role != null) this.setRoleId(role.getId());
	}

}
