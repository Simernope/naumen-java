package ru.simeon.NauJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.simeon.NauJava.models.TodoItem;
@RepositoryRestResource
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}