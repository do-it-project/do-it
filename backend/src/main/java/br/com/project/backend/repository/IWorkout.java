package br.com.project.backend.repository;

import br.com.project.backend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWorkout extends JpaRepository<Workout, Integer> {
    Optional<Workout> findByName(String name);
}
