package br.com.bixtecnologia.processadordeimagem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jose Julai Ritsure
 */
@JsonIgnoreProperties({"status", "filter", "userId", "user"})
public class ImageDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5670958767709191756L;
	
	private String originalFileName;
	
	private String filePath;
	
	private int width;
	
	private int height;
	
	private String status;
	
	private String filter;
	
	private Long userId;
	
	private UserDTO user;

	public ImageDTO() {
		super();
	}

	public ImageDTO(String filePath, int width, int height, String status, String filter, UserDTO user) {
		super();
		this.filePath = filePath;
		this.width = width;
		this.height = height;
		this.status = status;
		this.filter = filter;
		this.setUser(user);
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
		if(user != null) this.setUserId(user.getId());
	}

}
