package br.com.project.backend.DTO.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Comments cannot be blank")
    private String comments;

    @NotNull(message = "id_student cannot be null")
    private Integer id_student;
}
