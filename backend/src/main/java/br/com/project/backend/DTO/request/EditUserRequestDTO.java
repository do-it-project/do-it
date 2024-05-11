package br.com.project.backend.DTO.request;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequestDTO {

    @NotNull(message = "field id is required")
    private Integer id;

    @NotBlank(message = "The name field is required")
    private String name;

    @NotBlank(message = "The phone field is required")
    private String phone;

    @NotBlank(message = "The password field is required")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    @NotBlank(message = "The url_photo field is required")
    private String url_photo;

}
