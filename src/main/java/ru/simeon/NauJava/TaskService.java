package ru.simeon.NauJava;

public interface TaskService {
    void createTask(Long id, String description, String status, String deadline);
    Task findById(Long id);
    void deleteById(Long id);
    void updateTask(Long id, String newDescription, String newStatus, String newDeadline);
}
