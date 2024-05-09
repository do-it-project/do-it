package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.WorkoutExercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {
    private Integer id;
    private String name;
    private String comments;
    private UserDTO user;
    private List<WorkoutExercise> workout_exercises;
}
