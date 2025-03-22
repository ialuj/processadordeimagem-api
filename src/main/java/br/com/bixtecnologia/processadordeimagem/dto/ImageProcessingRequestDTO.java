package br.com.bixtecnologia.processadordeimagem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Jose Julai Ritsure
 */
public class ImageProcessingRequestDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7804802199644910862L;
	
	private Long imageId;
	
	private ImageDTO image;
	
	private String imageUrl;
	
	private Double resizePercentage;
	
	private String filter;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime requestTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime completionTime;

	public ImageProcessingRequestDTO() {
		super();
	}

	public ImageProcessingRequestDTO(ImageDTO image, String imageUrl, double resizePercentage, String filter,
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

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
		if(image != null) this.setImageId(image.getId());
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getResizePercentage() {
		return resizePercentage;
	}

	public void setResizePercentage(Double resizePercentage) {
		this.resizePercentage = resizePercentage;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
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
