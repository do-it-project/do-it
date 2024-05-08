package br.com.project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="workout_exercise")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "The repetitions field is required")
    @Column(name = "repetitions",length = 60, nullable = false)
    private Integer repetitions;

    @NotBlank(message = "The rest_pause field is required")
    @Column(name = "rest_pause", nullable = false)
    private Integer rest_pause;

    @NotBlank(message = "The sets field is required")
    @Column(name = "sets", nullable = false)
    private Integer sets;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
