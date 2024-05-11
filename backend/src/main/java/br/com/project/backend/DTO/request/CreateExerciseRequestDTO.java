package br.com.project.backend.DTO.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseRequestDTO {

    @NotBlank(message = "The name field is required")
    private String name;

    @NotBlank(message = "The description field is required")
    private String description;

    @NotBlank(message = "The link_tutorial field is required")
    private String link_tutorial;
}
