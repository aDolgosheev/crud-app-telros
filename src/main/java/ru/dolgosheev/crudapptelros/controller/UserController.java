package ru.dolgosheev.crudapptelros.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dolgosheev.crudapptelros.entity.User;
import ru.dolgosheev.crudapptelros.exception.UserNotFoundException;
import ru.dolgosheev.crudapptelros.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserRepository userRepository;

    public UserController(UserRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@Valid @RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User show(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    User update(@RequestBody @Valid User userUpdate, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdate.getName());
                    user.setSurname(userUpdate.getSurname());
                    user.setPatronymic(userUpdate.getPatronymic());
                    user.setDateOfBirth(userUpdate.getDateOfBirth());
                    user.setEmail(userUpdate.getEmail());
                    user.setPhoneNumber(userUpdate.getPhoneNumber());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
