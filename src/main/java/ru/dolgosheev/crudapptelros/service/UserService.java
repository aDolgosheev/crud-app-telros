package ru.dolgosheev.crudapptelros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dolgosheev.crudapptelros.dto.UserContactInfoDTO;
import ru.dolgosheev.crudapptelros.entity.User;
import ru.dolgosheev.crudapptelros.exception.UserNotFoundException;
import ru.dolgosheev.crudapptelros.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    };

    public User show(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

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

    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

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

    public List<User> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return repository.findAll();
    }
}
