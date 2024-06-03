package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.Student;
import br.com.project.backend.model.WorkoutExercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {
    private Integer id;
    private String name;
    private String comments;
    private String tag;
    private Integer id_student;
    private LocalDateTime creationDate;
}
