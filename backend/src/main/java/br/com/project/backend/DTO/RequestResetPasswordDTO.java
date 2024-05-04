package br.com.project.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestResetPasswordDTO {

    @NotBlank(message = "The email field is required")
    @Email(message = "Email is not valid")
    private String email;
}
