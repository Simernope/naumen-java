package ru.simeon.NauJava.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.simeon.NauJava.models.Report;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @EntityGraph(attributePaths = {"todoItems"})
    Optional<Report> findById(Long id);
}
