package br.com.project.backend.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkoutExerciseRequestDTO {

    @NotNull(message = "The repetitions field is required")
    private Integer repetitions;

    @NotNull(message = "The rest_pause field is required")
    private Integer rest_pause;

    @NotNull(message = "The sets field is required")
    private Integer sets;

    @NotNull(message = "The id_workout field is required")
    private Integer id_workout;

    @NotNull(message = "The id_exercise field is required")
    private Integer id_exercise;
}
