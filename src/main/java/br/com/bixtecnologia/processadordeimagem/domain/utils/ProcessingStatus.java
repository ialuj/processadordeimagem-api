package br.com.bixtecnologia.processadordeimagem.domain.utils;

import io.micrometer.common.util.StringUtils;

/**
 * @author Jose Julai Ritsure
 * Enum que representa o Estado de Processamento 
 */
public enum ProcessingStatus {
	
	PENDING(0, "Pending"), IN_PROGRESS(0, "In Progress"), COMPLETED(1, "Completed"), FAILED(1, "Failed"), CANCELLED(1, "Cancelled");

	private Integer code;

	private String description;

	private ProcessingStatus(Integer code, String description) {
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
	
	public static ProcessingStatus toEnum(String value) {
		if(value == null || StringUtils.isBlank(value)) return null;
		for (ProcessingStatus status: ProcessingStatus.values()) {
			if(value.equalsIgnoreCase(status.getDescription())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid Processing Status!");
	}

}
