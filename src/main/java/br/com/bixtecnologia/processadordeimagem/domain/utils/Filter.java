package br.com.bixtecnologia.processadordeimagem.domain.utils;

import io.micrometer.common.util.StringUtils;

/**
 * @author Jose Julai Ritsure
 * Enum que representa o Tipo de Filtro 
 */
public enum Filter {
	
	GRAYSCALE(0, "Grayscale"), SEPIA(1, "Sepia"), NONE(1, "None");

	private Integer code;

	private String description;

	private Filter(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Filter toEnum(String value) {
		if(value == null || StringUtils.isBlank(value)) return null;
		for (Filter filter: Filter.values()) {
			if(value.equalsIgnoreCase(filter.getDescription())) {
				return filter;
			}
		}
		throw new IllegalArgumentException("Invalid Filter!");
	}

}
