package br.com.project.backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="workout_exercise")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "The repetitions field is required")
    @Column(name = "repetitions",length = 60, nullable = false)
    private Integer repetitions;

    @NotNull(message = "The rest_pause field is required")
    @Column(name = "rest_pause", nullable = false)
    private Integer rest_pause;

    @NotNull(message = "The sets field is required")
    @Column(name = "sets", nullable = false)
    private Integer sets;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonBackReference
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonManagedReference
    private Exercise exercise;

    public WorkoutExercise(Integer repetitions, Integer rest_pause, Integer sets, Workout workout, Exercise exercise) {
        this.repetitions = repetitions;
        this.rest_pause = rest_pause;
        this.sets = sets;
        this.workout = workout;
        this.exercise = exercise;
    }
}
