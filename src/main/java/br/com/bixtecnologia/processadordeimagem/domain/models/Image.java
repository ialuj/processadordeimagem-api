package br.com.bixtecnologia.processadordeimagem.domain.models;

import br.com.bixtecnologia.processadordeimagem.domain.utils.Filter;
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
 * Classe que representa a Imagem
 */
@Entity
@Table(name = "images")
public class Image extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5150526449234366089L;

	@Column(name = "ORIGINAL_FILE_NAME", nullable = false)
	private String originalFileName;

	@Column(name = "FILE_PATH", nullable = false)
	private String filePath;

	@Column(name = "WIDTH", nullable = false)
	private int width;

	@Column(name = "HEIGHT", nullable = false)
	private int height;

	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProcessingStatus status;

	@Column(name = "FILTER", nullable = false)
	@Enumerated(EnumType.STRING)
	private Filter filter;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "USER_ID", updatable = false, insertable = false)
	private User user;

	public Image() {
		super();
	}

	public Image(String originalFileName, String filePath, int width, int height, ProcessingStatus status,
			Filter filter, User user) {
		super();
		this.originalFileName = originalFileName;
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

	public ProcessingStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessingStatus status) {
		this.status = status;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(user != null) this.setUserId(user.getId());
	}

}
