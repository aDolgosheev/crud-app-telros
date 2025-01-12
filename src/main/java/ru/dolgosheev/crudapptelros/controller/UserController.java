package ru.dolgosheev.crudapptelros.controller;

import lombok.RequiredArgsConstructor;
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
import ru.dolgosheev.crudapptelros.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/show-all") //Показать всех пользователей
    public List<User> findAll() { //Страница данного метода не требует авторизации
        return userService.findAll();
    }

    @GetMapping("/show-user/{id}") //Поиск пользователя по id - детальная информация
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public User show(@PathVariable Long id) {
        return userService.show(id);
    }

    @GetMapping("/show-user-contact-info/{id}") //Поиск пользователя по id - контактные данные
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public UserContactInfoDTO showContactInfo(@PathVariable Long id) {
        return userService.showContactInfo(id);
    }

    @PostMapping("/add-user") //Создать нового пользователя
    @PreAuthorize("hasAuthority('ROLE_USER')") //Страница данного метода требует авторизации с уровнем не ниже USER
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@RequestBody User newUser) {
        return userService.newUser(newUser);
    }

    @PutMapping("/update-user/{id}") //Обновить данные пользователя
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User userUpdate, @PathVariable Long id) {
        return userService.update(userUpdate, id);
    }

    @DeleteMapping("/delete/{id}") //Удалить пользователя
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //Страница данного метода требует авторизации с уровнем не ниже ADMIN
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<User> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
