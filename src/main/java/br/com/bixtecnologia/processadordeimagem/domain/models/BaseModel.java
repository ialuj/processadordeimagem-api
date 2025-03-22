package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * @author Jose Julai Ritsure
 * Super classe que sera herdada por todas as Entities Model
 */
@MappedSuperclass
public class BaseModel implements Serializable, Comparator<BaseModel> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7521528848594794062L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "UUID", unique = true, nullable = false)
	private String uuid;
	
	@Column(name = "CREATION_DATE", nullable = false)
	private LocalDateTime creationDate;
	
	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;
	
	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	public BaseModel() {
		super();
	}

	public BaseModel(LocalDateTime creationDate, String createdBy) {
		super();
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public BaseModel(LocalDateTime creationDate, String createdBy, LocalDateTime updateDate, String updatedBy) {
		super();
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
	}

	public BaseModel(Long id, String uuid, LocalDateTime creationDate, String createdBy, LocalDateTime updateDate,
			String updatedBy) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int compare(BaseModel current, BaseModel other) {
		if(current.creationDate == null || other.getCreationDate() == null) {
            return 0;
        }
        return current.creationDate.compareTo(other.getCreationDate());
	}
}
