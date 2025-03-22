package br.com.bixtecnologia.processadordeimagem.domain.models;

import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure Classe que representa um Servico
 */
@Entity
@Table(name = "services")
public class Service extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 549801680616274348L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PLAN", nullable = false)
	private SubscriptionPlan plan;

	@Column(name = "ACTIVE", nullable = false)
	private boolean isActive;

	public Service() {
		super();
	}

	public Service(String name, String description, SubscriptionPlan plan, boolean isActive) {
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

	public SubscriptionPlan getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = SubscriptionPlan.toEnum(plan);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
