package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.exception.WorkoutAlreadyExistsException;
import br.com.project.backend.mapper.WorkoutMapper;
import br.com.project.backend.model.Workout;
import br.com.project.backend.repository.IWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    @Autowired
    private IWorkout repository;

    @Autowired
    private WorkoutMapper workoutMapper;

    public List<WorkoutDTO> workoutsList(){
        List<Workout> tempWorkouts = this.repository.findAll();

        return workoutMapper.toDTOList(tempWorkouts);
    }

    public WorkoutDTO createWorkout(Workout workout){
        Optional<Workout> tempWorkout = this.findWorkoutByName(workout.getName());

        if(tempWorkout.isPresent()){
            throw new WorkoutAlreadyExistsException();
        }

        return workoutMapper.toDTO(this.repository.save(workout));
    }

    public void deleteWorkout(int id){
        this.repository.deleteById(id);
    }

    public Optional<Workout> findWorkoutByName(String name){
        return this.repository.findByName(name);
    }

    public Optional<Workout> findWorkoutById(int id){
        return this.repository.findById(id);
    }

}
