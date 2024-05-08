package br.com.project.backend.controller;

import br.com.project.backend.model.Exercise;
import br.com.project.backend.model.User;
import br.com.project.backend.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getExercises(){
        return ResponseEntity.ok(this.exerciseService.exerciseList());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@Valid @RequestBody Exercise ex){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.exerciseService.createExercise(ex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

        Optional<Exercise> tempEx = exerciseService.findExerciseById(id);

        if (tempEx.isPresent()) {
            exerciseService.deleteExercise(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
