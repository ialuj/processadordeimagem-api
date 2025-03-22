package br.com.bixtecnologia.processadordeimagem.dto;

import java.io.File;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Jose Julai Ritsure
 */
public class ImageProcessingResultDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7815637071623026487L;
	
	private Long imageProcessingRequestId;
	
	private ImageProcessingRequestDTO imageProcessingRequest;
	
	private String processedFileUrl;
	
	private File file;
	
	private String status;
	
	private String errorMessage;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime resultTime;

	public ImageProcessingResultDTO() {
		super();
	}

	public ImageProcessingResultDTO(ImageProcessingRequestDTO imageProcessingRequest, String processedFileUrl,
			String status, String errorMessage, LocalDateTime resultTime) {
		super();
		this.setImageProcessingRequest(imageProcessingRequest);
		this.processedFileUrl = processedFileUrl;
		this.status = status;
		this.errorMessage = errorMessage;
		this.resultTime = resultTime;
	}

	public Long getImageProcessingRequestId() {
		return imageProcessingRequestId;
	}

	public void setImageProcessingRequestId(Long imageProcessingRequestId) {
		this.imageProcessingRequestId = imageProcessingRequestId;
	}

	public ImageProcessingRequestDTO getImageProcessingRequest() {
		return imageProcessingRequest;
	}

	public void setImageProcessingRequest(ImageProcessingRequestDTO imageProcessingRequest) {
		this.imageProcessingRequest = imageProcessingRequest;
		if(imageProcessingRequest != null) this.setImageProcessingRequestId(imageProcessingRequest.getId());
	}

	public String getProcessedFileUrl() {
		return processedFileUrl;
	}

	public void setProcessedFileUrl(String processedFileUrl) {
		this.processedFileUrl = processedFileUrl;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getResultTime() {
		return resultTime;
	}

	public void setResultTime(LocalDateTime resultTime) {
		this.resultTime = resultTime;
	}

}
