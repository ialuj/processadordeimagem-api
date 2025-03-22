package br.com.bixtecnologia.processadordeimagem.dto;

/**
 * @author Jose Julai Ritsure
 */
public class AuthorityDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7570520382969834519L;
	
	private String code;
	
	private String description;

	public AuthorityDTO() {
		super();
	}

	public AuthorityDTO(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
