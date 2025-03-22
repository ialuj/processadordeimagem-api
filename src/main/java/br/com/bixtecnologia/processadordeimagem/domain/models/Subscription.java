package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.time.LocalDateTime;

import br.com.bixtecnologia.processadordeimagem.domain.utils.SubscriptionPlan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure 
 * Classe que representa uma Assinatura
 */
@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8995080985276586749L;

	@Enumerated(EnumType.STRING)
	@Column(name = "PLAN", nullable = false)
	private SubscriptionPlan plan;

	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "USER_ID", updatable = false, insertable = false)
	private User user;
	
	@Column(name = "ACTIVE", nullable = false)
	private Boolean isActive;
	
	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	public Subscription() {
		super();
	}

	public Subscription(SubscriptionPlan plan, User user, Boolean isActive) {
		super();
		this.plan = plan;
		this.setUser(user);
		this.isActive = isActive;
	}

	public SubscriptionPlan getPlan() {
		return plan;
	}

	public void setPlan(SubscriptionPlan plan) {
		this.plan = plan;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
