package ru.simeon.NauJava.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simeon.NauJava.models.Report;
import ru.simeon.NauJava.models.ReportData;
import ru.simeon.NauJava.models.ReportStatus;
import ru.simeon.NauJava.models.TodoItem;
import ru.simeon.NauJava.repositories.ReportRepository;
import ru.simeon.NauJava.repositories.TodoItemRepository;
import ru.simeon.NauJava.repositories.UserRepository;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private ReportRepository reportRepository;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public Long createReport() {
        // Создаем отчет со статусом "создан"
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        reportRepository.save(report);
        Long reportId = report.getId();

        // Запускаем асинхронное формирование отчета
        CompletableFuture.runAsync(() -> generateReport(reportId), executor);

        return reportId;
    }

    public void generateReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        long startTime = System.currentTimeMillis();

        // Асинхронные задачи для подсчета пользователей и сбора списка задач
        CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(() -> {
            long userStartTime = System.currentTimeMillis();
            long userCount = userRepository.count();
            report.setUserCountTime(System.currentTimeMillis() - userStartTime);
            return userCount;
        }, executor);

        CompletableFuture<List<TodoItem>> todoItemsFuture = CompletableFuture.supplyAsync(() -> {
            long todoStartTime = System.currentTimeMillis();
            List<TodoItem> todoItems = todoItemRepository.findAll();
            report.setTodoItemsTime(System.currentTimeMillis() - todoStartTime);
            return todoItems;
        }, executor);

        // Ожидаем завершения обеих задач
        try {
            long userCount = userCountFuture.join();
            List<TodoItem> todoItems = todoItemsFuture.join();

            // Заполняем отчет
            report.setUserCount(userCount);
            report.setTodoItems(todoItems);
            report.setContent("HTML or JSON representation of the report");

            // Устанавливаем статус завершен и сохраняем отчет
            report.setStatus(ReportStatus.COMPLETED);
        } catch (Exception e) {
            report.setStatus(ReportStatus.ERROR);
        } finally {
            report.setTotalTime(System.currentTimeMillis() - startTime);
            reportRepository.save(report);
        }
    }

    public ReportData getReportContent(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalStateException("Отчет не найден"));
        if (report.getStatus() == ReportStatus.CREATED) {
            throw new IllegalStateException("Отчет еще в процессе формирования.");
        } else if (report.getStatus() == ReportStatus.ERROR) {
            throw new IllegalStateException("Ошибка при формировании отчета.");
        }
        return new ReportData(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(() -> new IllegalStateException("Отчет не найден"));
    }

}
