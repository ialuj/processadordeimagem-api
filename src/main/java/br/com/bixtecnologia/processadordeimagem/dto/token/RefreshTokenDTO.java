package br.com.bixtecnologia.processadordeimagem.dto.token;

import java.io.Serializable;
import java.time.Instant;

public class RefreshTokenDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4979698320706864197L;
	
	private Long id;
	
	private String username;
	
	private String refreshToken;
	
	private Boolean revoked;
	
	private Instant dateCreated;
	
	public RefreshTokenDTO() {
		
	}

	public RefreshTokenDTO(Long id, String username, String refreshToken, Boolean revoked, Instant dateCreated) {
		super();
		this.id = id;
		this.username = username;
		this.refreshToken = refreshToken;
		this.revoked = revoked;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Boolean getRevoked() {
		return revoked;
	}

	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}

	public Instant getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Instant dateCreated) {
		this.dateCreated = dateCreated;
	}

}
