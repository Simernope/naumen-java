package ru.simeon.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.simeon.NauJava.models.User;
import ru.simeon.NauJava.repositories.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // Отображение страницы авторизации
    @GetMapping("/login")
    public String login() {
        return "login"; // Ссылка на login.html в папке templates
    }

    // Отображение страницы регистрации
    @GetMapping("/registration")
    public String registration() {
        return "registration"; // Ссылка на registration.html в папке templates
    }

    // Обработка регистрации
    @PostMapping("/registration")
    public String registerUser(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("message", "Регистрация прошла успешно! Теперь вы можете войти.");
        return "login";
    }
}
