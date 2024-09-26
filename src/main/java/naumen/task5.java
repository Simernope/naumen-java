package naumen;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

// Интерфейс Task из приложения
interface Task {
    void start();
    void stop();
}

// Класс task5, реализующий интерфейс Task
public class task5 implements Task {
    private String fileUrl; // URL файла для скачивания
    private String destinationPath; // Путь для сохранения файла
    private boolean isDownloading; // Флаг состояния загрузки
    private HttpClient client; // HTTP клиент для скачивания

    // Конструктор, принимающий URL файла и путь для сохранения
    public task5(String fileUrl, String destinationPath) {
        this.fileUrl = fileUrl;
        this.destinationPath = destinationPath;
        this.client = HttpClient.newHttpClient();
        this.isDownloading = false;
    }

    // Реализация метода start() для начала загрузки файла
    @Override
    public void start() {
        if (isDownloading) {
            System.out.println("Скачивание уже запущено.");
            return;
        }

        isDownloading = true;
        System.out.println("Начало загрузки файла...");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fileUrl))
                .build();

        CompletableFuture<HttpResponse<InputStream>> response = client.sendAsync(
                request, HttpResponse.BodyHandlers.ofInputStream());

        response.thenAccept(resp -> {
            try (BufferedInputStream in = new BufferedInputStream(resp.body());
                 FileOutputStream fileOutputStream = new FileOutputStream(destinationPath)) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1 && isDownloading) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }

                if (!isDownloading) {
                    System.out.println("Загрузка остановлена. Файл не был полностью скачан.");
                    new File(destinationPath).delete(); // Удаляем незавершенный файл
                } else {
                    System.out.println("Загрузка завершена.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).exceptionally(e -> {
            System.out.println("Ошибка при скачивании файла: " + e.getMessage());
            return null;
        });
    }

    // Реализация метода stop() для остановки загрузки файла
    @Override
    public void stop() {
        if (isDownloading) {
            isDownloading = false;
            System.out.println("Загрузка файла остановлена.");
        } else {
            System.out.println("Загрузка не была запущена.");
        }
    }

    // Пример использования
    public static void main(String[] args) {
        // Пример ссылки на файл и пути для сохранения
        String fileUrl = "https://example.com/sample.pdf";
        String destinationPath = "downloaded_file.pdf";

        // Создание и запуск задачи скачивания
        task5 downloadTask = new task5(fileUrl, destinationPath);
        downloadTask.start();

        // Остановка скачивания через 5 секунд (пример)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        downloadTask.stop();
    }
}
