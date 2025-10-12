package com.fase2.meta.model;

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
}
