package br.com.project.backend.controller;

import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.DTO.request.CreateWorkoutRequestDTO;
import br.com.project.backend.DTO.request.EditWorkoutRequestDTO;
import br.com.project.backend.model.Workout;
import br.com.project.backend.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<Workout>> getWorkoutsList(){
        return ResponseEntity.ok(this.workoutService.workoutsList());
    }

    @PutMapping
    public ResponseEntity<WorkoutDTO> editWorkout(@Valid @RequestBody EditWorkoutRequestDTO workout){
        return ResponseEntity.status(HttpStatus.OK).body(workoutService.editWorkout(workout));
    }

    @PostMapping
    public ResponseEntity<WorkoutDTO> createWorkout(@Valid @RequestBody CreateWorkoutRequestDTO workout){
        return ResponseEntity.status(HttpStatus.CREATED).body(workoutService.createWorkout(workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable("id") int id) {

        Optional<Workout> tempWorkout = workoutService.findWorkoutById(id);

        if (tempWorkout.isPresent()) {
            workoutService.deleteWorkout(tempWorkout.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
