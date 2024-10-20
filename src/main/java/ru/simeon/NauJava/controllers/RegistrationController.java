package ru.simeon.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.simeon.NauJava.models.User;
import ru.simeon.NauJava.services.UserService;

@Controller
public class RegistrationController
{
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public User registerUser(User user) throws Exception {
            userService.addUser(user);
            return user;
    }
}