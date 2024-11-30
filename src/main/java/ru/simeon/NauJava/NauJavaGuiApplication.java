package ru.simeon.NauJava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class NauJavaGuiApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создаем WebView
        WebView webView = new WebView();

        // Указываем URL вашего Spring Boot приложения
        String appUrl = "http://localhost:8080"; // Порт вашего приложения

        // Загружаем страницу
        webView.getEngine().load(appUrl);

        // Создаем сцену и добавляем WebView
        Scene scene = new Scene(webView, 1024, 768);
        primaryStage.setTitle("Spring Boot GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Запускаем JavaFX приложение
        launch(args);
    }
}
