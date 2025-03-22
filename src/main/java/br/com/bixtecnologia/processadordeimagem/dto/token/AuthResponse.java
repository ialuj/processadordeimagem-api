package br.com.bixtecnologia.processadordeimagem.dto.token;

import java.util.Set;

public class AuthResponse {
	
    private String username;
    private String password;
    private String token;
    private String refreshToken;
    private Set<String> roles;

    public AuthResponse() {
    }

    public AuthResponse(String username, String password, String token, String refreshToken, Set<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
		this.refreshToken = refreshToken;
		this.roles = roles;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

