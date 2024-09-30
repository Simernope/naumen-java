package ru.simeon.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class CommandLineAppConfig {
    @Autowired
    private TaskService taskService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }

                    // Изменение разбора строки
                    String[] cmd = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                    switch (cmd[0]) {
                        case "create" -> {
                            // Считывание ID задачи
                            Long id = Long.valueOf(cmd[1]);

                            // Описание задачи — удаляем кавычки
                            String description = cmd[2].replace("\"", "");

                            // Статус и дедлайн
                            String status = cmd[3].replace("\"", "");  // Убираем кавычки из статуса
                            String deadline = cmd[4].replace("\"", "");  // Убираем кавычки из дедлайна

                            taskService.createTask(id, description, status, deadline);
                            System.out.println("Задача успешно добавлена...");
                        }
                        case "read" -> {
                            Task task = taskService.findById(Long.valueOf(cmd[1]));
                            if (task != null) {
                                System.out.println("Задача: \"" + task.getDescription() + "\", статус: \"" + task.getStatus() + "\", дедлайн: \"" + task.getDeadline() + "\"");
                            } else {
                                System.out.println("Задача не найдена.");
                            }
                        }
                        case "update" -> {
                            // Убираем лишние кавычки при обновлении
                            String description = cmd[2].replace("\"", "");
                            String status = cmd[3].replace("\"", "");
                            String deadline = cmd[4].replace("\"", "");

                            taskService.updateTask(Long.valueOf(cmd[1]), description, status, deadline);
                            System.out.println("Задача успешно обновлена...");
                        }
                        case "delete" -> {
                            taskService.deleteById(Long.valueOf(cmd[1]));
                            System.out.println("Задача успешно удалена...");
                        }
                        default -> System.out.println("Неизвестная команда...");
                    }
                }
            }
        };
    }
}
