package br.com.project.backend.repository;

import br.com.project.backend.model.Exercise;
import br.com.project.backend.model.Workout;
import br.com.project.backend.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkoutExercise extends JpaRepository<WorkoutExercise, Integer> {
    Optional<WorkoutExercise> findByWorkoutAndExercise(Workout workout,Exercise exercise);
}
