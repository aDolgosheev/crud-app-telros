package ru.dolgosheev.crudapptelros.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dolgosheev.crudapptelros.entity.User;
import ru.dolgosheev.crudapptelros.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> all() {
        return repository.findAll();
    }
}
