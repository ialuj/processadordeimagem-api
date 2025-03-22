package br.com.bixtecnologia.processadordeimagem.dto;

/**
 * @author Jose Julai Ritsure
 */
public class ServiceDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7389549686359225678L;
	
	private String name;
	
	private String description;
	
	private String plan;
	
	private boolean isActive;

	public ServiceDTO() {
		super();
	}

	public ServiceDTO(String name, String description, String plan, boolean isActive) {
		super();
		this.name = name;
		this.description = description;
		this.plan = plan;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
