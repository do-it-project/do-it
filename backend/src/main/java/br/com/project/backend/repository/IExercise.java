package br.com.project.backend.repository;

import br.com.project.backend.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IExercise extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByName(String name);
}