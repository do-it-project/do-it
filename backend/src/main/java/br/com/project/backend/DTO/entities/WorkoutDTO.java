package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {
    private Integer id;
    private String name;
    private String comments;
    private UserDTO user;
}
