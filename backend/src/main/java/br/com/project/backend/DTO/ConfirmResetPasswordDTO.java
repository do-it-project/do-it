package br.com.project.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConfirmResetPasswordDTO {

    private String token;

    private String email;

    @NotBlank(message = "The password field is required")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
}
