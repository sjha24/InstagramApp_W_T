package com.saurav.InstagramApp.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String password;
    private Integer age;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;
}
