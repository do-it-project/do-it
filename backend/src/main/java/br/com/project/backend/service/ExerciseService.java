package br.com.project.backend.service;

import br.com.project.backend.DTO.request.CreateExerciseRequestDTO;
import br.com.project.backend.exception.ExerciseAlreadyExistsException;
import br.com.project.backend.model.Exercise;
import br.com.project.backend.repository.IExercise;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExerciseService {

    @Autowired
    private IExercise repository;

    @Transactional
    public List<Exercise> exerciseList(){   
        return this.repository.findAll();
    }

    @Transactional
    public Exercise createExercise(CreateExerciseRequestDTO ex){
        Optional<Exercise> tempEx = this.repository.findByName(ex.getName());

        if(tempEx.isPresent()){
            throw new ExerciseAlreadyExistsException();
        }

        Exercise exercise = new Exercise(ex.getName(), ex.getDescription(), ex.getLink_tutorial());

        return this.repository.save(exercise);
    }

    @Transactional
    public void deleteExercise(Exercise exercise){
        this.repository.delete(exercise);
    }

    public Optional<Exercise> findExerciseByName(String name){
        return this.repository.findByName(name);
    }

    public Optional<Exercise> findExerciseById(int id){
        return this.repository.findById(id);
    }


}

