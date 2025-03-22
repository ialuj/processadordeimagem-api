package br.com.bixtecnologia.processadordeimagem.domain.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Jose Julai Ritsure
 * Enum que representa o Tipo de Assinatura
 */
public enum SubscriptionPlan {

    BASIC(0, "Basic"), 
    PREMIUM(1, "Premium");

    private final Integer code;
    private final String description;

    private SubscriptionPlan(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static SubscriptionPlan toEnum(Integer code) {
        if (code == null) return null;
        for (SubscriptionPlan plan : SubscriptionPlan.values()) {
            if (code.equals(plan.getCode())) {
                return plan;
            }
        }
        throw new IllegalArgumentException("Invalid Plan!");
    }

    public static SubscriptionPlan toEnum(String value) {
        if (StringUtils.isBlank(value)) return null;
        for (SubscriptionPlan plan : SubscriptionPlan.values()) {
            if (value.equalsIgnoreCase(plan.getDescription())) {
                return plan;
            }
        }
        throw new IllegalArgumentException("Invalid Plan!");
    }
}
