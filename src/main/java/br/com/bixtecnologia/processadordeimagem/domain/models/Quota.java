package br.com.bixtecnologia.processadordeimagem.domain.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Jose Julai Ritsure
 * Classe que representa uma Quota 
 */
@Entity
@Table(name = "quotas")
public class Quota extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8296419616103150044L;
	
	@Column(name = "SUBSCRIPTION_ID", nullable = false)
	private Long subscriptionId;
	
	@ManyToOne
	@JoinColumn(name = "SUBSCRIPTION_ID", updatable = false, insertable = false)
	private Subscription subscription;
	
	@Column(name = "QUOTA", nullable = false)
	private int quota;
	
	@Column(name = "LAST_RESET_DATE", nullable = false)
	private LocalDateTime lastResetDate;

	public Quota() {
		super();
	}

	public Quota(Subscription subscription, int quota, LocalDateTime lastResetDate) {
		super();
		this.setSubscription(subscription);
		this.quota = quota;
		this.lastResetDate = lastResetDate;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
		if(subscription != null) this.setSubscriptionId(subscription.getId());
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public LocalDateTime getLastResetDate() {
		return lastResetDate;
	}

	public void setLastResetDate(LocalDateTime lastResetDate) {
		this.lastResetDate = lastResetDate;
	}

}
