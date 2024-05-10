package br.com.project.backend.controller;

import br.com.project.backend.DTO.request.CreateWorkoutExerciseRequestDTO;
import br.com.project.backend.model.WorkoutExercise;
import br.com.project.backend.service.WorkoutExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/workouts/exercises")
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    @PostMapping
    public ResponseEntity<?> createWorkoutExercises(@Valid @RequestBody List<CreateWorkoutExerciseRequestDTO> weList) {
        this.workoutExerciseService.createWorkoutExercises(weList);

        return ResponseEntity.ok("Exercises created for workout successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkoutExercises(@PathVariable("id") Integer id) {

        Optional<WorkoutExercise> tempWorkoutExercise = this.workoutExerciseService.findWorkoutExerciseById(id);

        if (tempWorkoutExercise.isPresent()) {
            this.workoutExerciseService.deleteWorkoutExerciseById(tempWorkoutExercise.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
