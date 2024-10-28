package ru.simeon.NauJava.models;

public enum ReportStatus {
    CREATED("Создан"),
    COMPLETED("Завершен"),
    ERROR("Ошибка");

    private final String status;

    ReportStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
