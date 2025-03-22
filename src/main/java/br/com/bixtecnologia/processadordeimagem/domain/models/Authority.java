package br.com.bixtecnologia.processadordeimagem.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 * Classe que representa uma permissao de um usuario
 */
@Entity
@Table(name = "authorities")
public class Authority extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6866753787842991379L;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Authority() {
		super();
	}

	public Authority(String code, String description) {
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
