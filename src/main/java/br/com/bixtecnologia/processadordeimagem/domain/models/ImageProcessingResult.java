package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.time.LocalDateTime;

import br.com.bixtecnologia.processadordeimagem.domain.utils.ProcessingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 * Classe que representa o ressultado de processamento da imagem 
 */
@Entity
@Table(name = "image_processing_results")
public class ImageProcessingResult extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5583451005822240079L;
	
	@Column(name = "IMAGE_PROCESSING_REQUEST_ID", nullable = false)
	private Long imageProcessingRequestId;
	
	@ManyToOne
    @JoinColumn(name = "IMAGE_PROCESSING_REQUEST_ID", updatable = false, insertable = false)
    private ImageProcessingRequest imageProcessingRequest;
	
	@Column(name = "PROCESSED_FILE_URL")
    private String processedFileUrl;
    
    @Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProcessingStatus status;
    
    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "RESULT_TIME")
    private LocalDateTime resultTime;

	public Long getImageProcessingRequestId() {
		return imageProcessingRequestId;
	}

	public void setImageProcessingRequestId(Long imageProcessingRequestId) {
		this.imageProcessingRequestId = imageProcessingRequestId;
	}

	public ImageProcessingRequest getImageProcessingRequest() {
		return imageProcessingRequest;
	}

	public void setImageProcessingRequest(ImageProcessingRequest imageProcessingRequest) {
		this.imageProcessingRequest = imageProcessingRequest;
		if(imageProcessingRequest != null) this.setImageProcessingRequestId(imageProcessingRequest.getId());
	}

	public String getProcessedFileUrl() {
		return processedFileUrl;
	}

	public void setProcessedFileUrl(String processedFileUrl) {
		this.processedFileUrl = processedFileUrl;
	}

	public ProcessingStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessingStatus status) {
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
