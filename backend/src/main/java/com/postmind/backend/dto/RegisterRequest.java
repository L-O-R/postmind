package com.postmind.backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class RegisterRequest {
    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Username is Required")
    private String username;

    @Email(message = "Invalid Email")
    @NotBlank(message = "email is Required")
    private String email;

    @Size(min=6 , message = "Password must be at least 6 characters")
    private String password;


}
