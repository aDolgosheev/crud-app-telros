package ru.dolgosheev.crudapptelros.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dolgosheev.crudapptelros.dto.UserContactInfoDTO;
import ru.dolgosheev.crudapptelros.entity.User;
import ru.dolgosheev.crudapptelros.exception.UserNotFoundException;
import ru.dolgosheev.crudapptelros.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/show-all") //Показать всех пользователей
    public List<User> findAll() { //Страница данного метода не требует авторизации
        return repository.findAll();
    }

    @GetMapping("/show-user/{id}") //Поиск пользователя по id - детальная информация
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public User show(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    @GetMapping("/show-user-contact-info/{id}") //Поиск пользователя по id - контактные данные
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public UserContactInfoDTO showContactInfo(@PathVariable Long id) {

        var user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        var dto = new UserContactInfoDTO();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPatronymic(user.getPatronymic());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    @PostMapping("/add-user") //Создать нового пользователя
    @PreAuthorize("hasAuthority('ROLE_USER')") //Страница данного метода требует авторизации с уровнем не ниже USER
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/update-user/{id}") //Обновить данные пользователя
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User userUpdate, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setName(userUpdate.getName());
                    user.setSurname(userUpdate.getSurname());
                    user.setPatronymic(userUpdate.getPatronymic());
                    user.setDateOfBirth(userUpdate.getDateOfBirth());
                    user.setEmail(userUpdate.getEmail());
                    user.setPhoneNumber(userUpdate.getPhoneNumber());
                    return repository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}") //Удалить пользователя
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //Страница данного метода требует авторизации с уровнем не ниже ADMIN
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<User> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }
}
