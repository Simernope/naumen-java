package ru.simeon.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepository implements CrudRepository<Task, Long> {
    private final List<Task> taskContainer;

    @Autowired
    public TaskRepository(List<Task> taskContainer) {
        this.taskContainer = taskContainer;
    }

    @Override
    public void create(Task task) {
        taskContainer.add(task);
    }

    @Override
    public Task read(Long id) {
        return taskContainer.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void update(Task task) {
        Task existingTask = read(task.getId());
        if (existingTask != null) {
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setDeadline(task.getDeadline());
        }
    }

    @Override
    public void delete(Long id) {
        taskContainer.removeIf(task -> task.getId().equals(id));
    }
}
