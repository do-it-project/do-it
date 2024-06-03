package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.WorkoutDTO;
import br.com.project.backend.DTO.request.CreateWorkoutRequestDTO;
import br.com.project.backend.DTO.request.EditWorkoutRequestDTO;
import br.com.project.backend.exception.StudentNotFoundException;
import br.com.project.backend.exception.WorkoutAlreadyExistsException;
import br.com.project.backend.exception.WorkoutNotExistsException;
import br.com.project.backend.mapper.UserMapper;
import br.com.project.backend.mapper.WorkoutMapper;
import br.com.project.backend.model.Student;
import br.com.project.backend.model.Workout;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.repository.IWorkout;
import br.com.project.backend.utils.DateUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;;

@Service
public class WorkoutService {

    @Autowired
    private IWorkout repository;

    @Autowired
    private WorkoutMapper workoutMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUser userRepository;

//    @Transactional
//    public List<WorkoutDTO> workoutsList(){
//        List<Workout> tempWorkouts = this.repository.findAll();
//
//        return workoutMapper.toDTOList(tempWorkouts);
//    }

    @Transactional
    public List<Workout> workoutsList(){
        List<Workout> tempWorkouts = this.repository.findAll();

        return tempWorkouts;
    }

    @Transactional
    public WorkoutDTO createWorkout(CreateWorkoutRequestDTO workout){
        Optional<Workout> tempWorkout = this.findWorkoutByName(workout.getName());

        if(tempWorkout.isPresent()){
            throw new WorkoutAlreadyExistsException();
        }

        Student tempStudent = this.userRepository.findStudentById(workout.getId_student());

        if(tempStudent == null){
            throw new StudentNotFoundException("Student not found");
        }

        LocalDateTime creationDate = LocalDateTime.now();

        Workout newWorkout = new Workout(
                workout.getName(), workout.getComments(), workout.getTag(), tempStudent ,
                new DateUtils().formatDate(creationDate)
        );

        return workoutMapper.toDTO(this.repository.save(newWorkout));
    }

    @Transactional
    public WorkoutDTO editWorkout(EditWorkoutRequestDTO workout){

        Optional<Workout> tempWorkout = this.repository.findById(workout.getId());

        if(tempWorkout.isEmpty()){
            throw new WorkoutNotExistsException();
        }

        tempWorkout.get().setComments(workout.getComments());
        tempWorkout.get().setName(workout.getName());

        return workoutMapper.toDTO(this.repository.save(tempWorkout.get()));
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
