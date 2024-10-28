package ru.simeon.NauJava.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long userCount;         // Количество пользователей
    private long userCountTime;     // Время на подсчет пользователей
    private long todoItemsTime;     // Время на получение списка задач
    private long totalTime;         // Общее время на формирование отчета

    @OneToMany // Пример, предполагая, что TodoItem также является сущностью в БД.
    private List<TodoItem> todoItems;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public long getUserCountTime() {
        return userCountTime;
    }

    public void setUserCountTime(long userCountTime) {
        this.userCountTime = userCountTime;
    }

    public long getTodoItemsTime() {
        return todoItemsTime;
    }

    public void setTodoItemsTime(long todoItemsTime) {
        this.todoItemsTime = todoItemsTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}
