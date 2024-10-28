package ru.simeon.NauJava.models;

import java.util.List;

public class ReportData {
    private String message;
    private Long userCount;
    private List<TodoItem> todoItems;
    private long userCountTime;
    private long todoItemsTime;
    private long totalTime;

    // Конструктор для успешного отчета
    public ReportData(Report report) {
        this.userCount = report.getUserCount();
        this.todoItems = report.getTodoItems();
        this.userCountTime = report.getUserCountTime();
        this.todoItemsTime = report.getTodoItemsTime();
        this.totalTime = report.getTotalTime();
    }

    // Конструктор для сообщения об ошибке
    public ReportData(String message) {
        this.message = message;
    }

    public long getUserCount() {
        return userCount;
    }

    public long getUserCountTime() {
        return userCountTime;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public long getTodoItemsTime() {
        return todoItemsTime;
    }

    public long getTotalTime() {
        return totalTime;
    }
}
