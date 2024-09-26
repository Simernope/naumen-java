package naumen;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class task4 {
    public static void main(String[] args) {
        // Создаем HTTP клиент
        HttpClient client = HttpClient.newHttpClient();

        // Создаем запрос по адресу https://httpbin.org/user-agent
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/user-agent"))
                .GET()
                .build();

        try {
            // Выполняем запрос и получаем ответ
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Находим и выводим строку user-agent из ответа
            String responseBody = response.body();
            int startIndex = responseBody.indexOf(": \"") + 3;
            int endIndex = responseBody.indexOf("\"", startIndex);
            String userAgent = responseBody.substring(startIndex, endIndex);

            System.out.println("User-Agent: " + userAgent);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
