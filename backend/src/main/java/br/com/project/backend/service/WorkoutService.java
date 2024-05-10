package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.exception.WorkoutAlreadyExistsException;
import br.com.project.backend.mapper.WorkoutMapper;
import br.com.project.backend.model.Workout;
import br.com.project.backend.repository.IWorkout;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;;

@Service
public class WorkoutService {

    @Autowired
    private IWorkout repository;

    @Autowired
    private WorkoutMapper workoutMapper;

    @Transactional
    public List<WorkoutDTO> workoutsList(){
        List<Workout> tempWorkouts = this.repository.findAll();

        return workoutMapper.toDTOList(tempWorkouts);
    }

    @Transactional
    public WorkoutDTO createWorkout(Workout workout){
        Optional<Workout> tempWorkout = this.findWorkoutByName(workout.getName());

        if(tempWorkout.isPresent()){
            throw new WorkoutAlreadyExistsException();
        }

        return workoutMapper.toDTO(this.repository.save(workout));
    }

    @Transactional
    public WorkoutDTO editWorkout(Workout workout){
        return workoutMapper.toDTO(this.repository.save(workout));
    }

    @Transactional
    public void deleteWorkout(Workout workout){
        this.repository.delete(workout);
    }

    public Optional<Workout> findWorkoutByName(String name){
        return this.repository.findByName(name);
    }

    public Optional<Workout> findWorkoutById(int id){
        return this.repository.findById(id);
    }

}
