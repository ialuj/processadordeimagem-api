package br.com.bixtecnologia.processadordeimagem.dto.token;

public class TokensResponse {
	
	private String accessToken;
	
	private String refreshToken;

	public TokensResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TokensResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

}
