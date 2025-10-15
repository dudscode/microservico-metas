package com.fase2.meta.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusGoal {
    PENDING("Pendente"),
    FINISHED("Finalizada");
    private String status;
    StatusGoal(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    @JsonValue
    public String toJson() {
        return status;
    }

    @JsonCreator
    public static StatusGoal from(String value) {
        if (value == null) return null;
        String v = value.trim();
        for (StatusGoal s : values()) {
            if (s.name().equalsIgnoreCase(v)) {
                return s;
            }
        }

        for (StatusGoal s : values()) {
            if (s.status.equalsIgnoreCase(v)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown StatusGoal: " + value);
    }
}
