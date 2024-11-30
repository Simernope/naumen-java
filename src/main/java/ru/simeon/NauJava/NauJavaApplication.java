package ru.simeon.NauJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NauJavaApplication {

	public static void main(String[] args) {
		// Запускаем Spring Boot приложение
		SpringApplication.run(NauJavaApplication.class, args);

		// Запускаем JavaFX GUI
		NauJavaGuiApplication.main(args);
	}
}
