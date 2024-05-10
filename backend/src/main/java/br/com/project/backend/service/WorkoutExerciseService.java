package br.com.project.backend.service;

import br.com.project.backend.DTO.request.CreateWorkoutExerciseRequestDTO;
import br.com.project.backend.exception.WorkoutExerciseAlreadyExistsException;
import br.com.project.backend.model.Exercise;
import br.com.project.backend.model.Workout;
import br.com.project.backend.model.WorkoutExercise;
import br.com.project.backend.repository.IExercise;
import br.com.project.backend.repository.IWorkout;
import br.com.project.backend.repository.IWorkoutExercise;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutExerciseService {
    @Autowired
    private IWorkoutExercise repository;

    @Autowired
    private IWorkout repositoryWorkout;

    @Autowired
    private IExercise repositoryExercise;

    public List<WorkoutExercise> workoutExerciseList(){
        return this.repository.findAll();
    }

    @Transactional
    public void createWorkoutExercises(List<CreateWorkoutExerciseRequestDTO> weList){

        List<WorkoutExercise> workoutExercises = new ArrayList<>();

        for(int i=0 ; i<weList.size() ; i++){
            for(int j=i+1 ; j<weList.size(); j++){
                if(weList.get(i).getId_workout() == weList.get(j).getId_workout() && weList.get(i).getId_exercise() == weList.get(j).getId_exercise()){
                    throw new WorkoutExerciseAlreadyExistsException("Exercise duplicated id: " + weList.get(i).getId_exercise());
                }
            }
        }

        for(CreateWorkoutExerciseRequestDTO dto: weList){
            Optional<Workout> tempWorkout = this.repositoryWorkout.findById(dto.getId_workout());

            if(tempWorkout.isEmpty()){
                throw new RuntimeException("Workout don't exists");
            }

            Optional<Exercise> tempExercise = this.repositoryExercise.findById(dto.getId_exercise());

            if(tempExercise.isEmpty()){
                throw new RuntimeException("Exercise don't exists");
            }

            workoutExercises.add(new WorkoutExercise(
                    dto.getRepetitions(),
                    dto.getRest_pause(),
                    dto.getSets(),
                    tempWorkout.get(),
                    tempExercise.get()));
        }

        for(WorkoutExercise we : workoutExercises) {
            Optional<WorkoutExercise> tempWE =
                    this.repository.findByWorkoutAndExercise(we.getWorkout(), we.getExercise());

            if(tempWE.isPresent()){
                throw new WorkoutExerciseAlreadyExistsException("Exercise "
                        + tempWE.get().getExercise().getName() + " for "
                        + tempWE.get().getWorkout().getName() + " already exists");
            }
        }

        this.repository.saveAll(workoutExercises);
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
