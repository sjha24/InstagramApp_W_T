package com.saurav.InstagramApp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String password;
    private Integer age;
    @Column(unique = true)
    @Email
    private String email;
    @Pattern(regexp = "\\d{10}", message = "Phone number Invalid")
    private String phoneNumber;
    public User(String firstName, String lastName, String password, Integer age, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}