package br.com.project.backend.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutExerciseRequestDTO {
    private Integer repetitions;
    private Integer rest_pause;
    private Integer sets;
    private Integer id_workout;
    private Integer id_exercise;
}
