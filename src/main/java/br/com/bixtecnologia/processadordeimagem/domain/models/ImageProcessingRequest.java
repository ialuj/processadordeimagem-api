package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.time.LocalDateTime;

import br.com.bixtecnologia.processadordeimagem.domain.utils.Filter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 * Classe que representa a requisicao do processamento da imagem 
 */
@Entity
@Table(name = "image_processing_requests")
public class ImageProcessingRequest extends BaseModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8498074682913934683L;

	@Column(name = "IMAGE_ID", nullable = false)
	private Long imageId;
	
	@ManyToOne
	@JoinColumn(name = "IMAGE_ID", updatable = false, insertable = false)
	private Image image;
	
	@Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;
    
	@Column(name = "RESIZE_PERCENTAGE", nullable = false)
    private double resizePercentage;
    
	@Column(name = "FILTER", nullable = false)
    private Filter filter;
	
	@Column(name = "REQUEST_TIME", nullable = false)
	private LocalDateTime requestTime;
	
	@Column(name = "COMPLETION_TIME", nullable = true)
    private LocalDateTime completionTime;

	public ImageProcessingRequest() {
		super();
	}

	public ImageProcessingRequest(Image image, String imageUrl, double resizePercentage, Filter filter,
			LocalDateTime requestTime) {
		super();
		this.setImage(image);
		this.imageUrl = imageUrl;
		this.resizePercentage = resizePercentage;
		this.filter = filter;
		this.requestTime = requestTime;
	}

	public ImageProcessingRequest(Image image, String imageUrl, double resizePercentage, Filter filter,
			LocalDateTime requestTime, LocalDateTime completionTime) {
		super();
		this.setImage(image);
		this.imageUrl = imageUrl;
		this.resizePercentage = resizePercentage;
		this.filter = filter;
		this.requestTime = requestTime;
		this.completionTime = completionTime;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		if(image != null) this.setImageId(image.getId());
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getResizePercentage() {
		return resizePercentage;
	}

	public void setResizePercentage(double resizePercentage) {
		this.resizePercentage = resizePercentage;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public LocalDateTime getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(LocalDateTime completionTime) {
		this.completionTime = completionTime;
	}

}
