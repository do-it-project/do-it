package br.com.project.backend.service;

import br.com.project.backend.exception.WorkoutExerciseAlreadyExistsException;
import br.com.project.backend.model.WorkoutExercise;
import br.com.project.backend.repository.IWorkoutExercise;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutExerciseService {
    @Autowired
    private IWorkoutExercise repository;

    public List<WorkoutExercise> workoutExerciseList(){
        return this.repository.findAll();
    }

    @Transactional
    public void createWorkoutExercises(List<WorkoutExercise> weList){
        for(WorkoutExercise we : weList){
            Optional<WorkoutExercise> tempWE =
                    this.repository.findByWorkoutAndExercise(we.getWorkout(), we.getExercise());

            if(tempWE.isPresent()){
                throw new WorkoutExerciseAlreadyExistsException("Exercise "
                        + tempWE.get().getExercise().getName() + " for "
                        + tempWE.get().getWorkout().getName() + " already exists");
            }
        }

        this.repository.saveAll(weList);
    }

    @Transactional
    public void deleteWorkoutExerciseById(Integer id){
        this.repository.deleteById(id);
    }

    @Transactional
    public void deleteWorkoutExercise(WorkoutExercise workoutExercise){
        this.repository.delete(workoutExercise);
    }

    public Optional<WorkoutExercise> findWorkoutExerciseById(int id){
        return this.repository.findById(id);
    }

}
