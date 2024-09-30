package ru.simeon.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(Long id, String description, String status, String deadline) {
        Task newTask = new Task();
        newTask.setId(id);
        newTask.setDescription(description);
        newTask.setStatus(status);
        newTask.setDeadline(deadline);
        taskRepository.create(newTask);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void updateTask(Long id, String newDescription, String newStatus, String newDeadline) {
        Task updatedTask = new Task();
        updatedTask.setId(id);
        updatedTask.setDescription(newDescription);
        updatedTask.setStatus(newStatus);
        updatedTask.setDeadline(newDeadline);
        taskRepository.update(updatedTask);
    }
}
