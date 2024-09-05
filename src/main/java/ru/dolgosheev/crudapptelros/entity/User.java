package ru.dolgosheev.crudapptelros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // ФИО начинается с заглавной буквы и имеет от 2 до 20 символов
    @NotEmpty(message = "The name can't be empty")  //Если формат имени неверный, то выскочит данное сообщение
                                                    //Показал для примера, далее сообщения не вводил, чтобы не тратить время
    @Pattern(regexp = "^[A-Z][a-z]{1,19}$")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]{1,19}$")
    private String surname;

    @Pattern(regexp = "^[A-Z][a-z]{1,19}$")
    private String patronymic;

    @Pattern(regexp = "([0-9]{2}).([0-9]{2}).([0-9]{4})") //Формат вводимой даты dd.mm.yyyy
    private String dateOfBirth;

    @NotEmpty
    @Email()
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Pattern(regexp = "^\\+7[0-9]{10}$") //Учитываются только номера с кодом РФ и должны иметь формат +7ХХХХХХХХХХ
    @Column(unique = true)
    private String phoneNumber;

}
