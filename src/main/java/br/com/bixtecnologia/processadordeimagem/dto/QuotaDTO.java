package br.com.bixtecnologia.processadordeimagem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Jose Julai Ritsure
 */
public class QuotaDTO extends BaseModelDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5317601059467137283L;
	
	private Long subscriptionId;
	
	private SubscriptionDTO subscription;
	
	private int quota;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime lastResetDate;

	public QuotaDTO() {
		super();
	}

	public QuotaDTO(SubscriptionDTO subscription, int quota, LocalDateTime lastResetDate) {
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

	public SubscriptionDTO getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionDTO subscription) {
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
