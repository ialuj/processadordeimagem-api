package br.com.bixtecnologia.processadordeimagem.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;

/**
 * @author Jose Julai Ritsure
 */
@JsonIgnoreProperties({"userId", "user", "isActive", "startDate", "endDate"})
public class SubscriptionDTO extends BaseModelDTO {

    private static final long serialVersionUID = -3636757415710844528L;

    @NotNull(message = "Indique o plano")
    private String plan;

    private Long userId;
    private UserDTO user;
    private Boolean isActive;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;

    public SubscriptionDTO() {
        super();
    }

    public SubscriptionDTO(String plan, UserDTO user, Boolean isActive) {
        super();
        this.plan = plan;
        this.setUser(user);
        this.isActive = isActive;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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
        if (user != null) this.setUserId(user.getId());
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
