package ru.simeon.NauJava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simeon.NauJava.models.TodoItem;
import ru.simeon.NauJava.repositories.TodoItemRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public Iterable<TodoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(Instant.now());
        }
        todoItem.setUpdatedAt(Instant.now());
        return todoItemRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }

    public List<TodoItem> getAllItems() {
        return todoItemRepository.findAll();
    }

}