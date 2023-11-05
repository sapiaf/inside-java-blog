package dev.sapia.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotNull
    @PastOrPresent
    private LocalDate registrationDate;


    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
