package br.com.project.backend.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String password;

    private String url_photo;

}
