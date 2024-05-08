package br.com.project.backend.service;

import br.com.project.backend.exception.ExerciseAlreadyExistsException;
import br.com.project.backend.model.Exercise;
import br.com.project.backend.repository.IExercise;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private IExercise repository;

    public List<Exercise> exerciseList(){
        return this.repository.findAll();
    }

    @Transactional
    public Exercise createExercise(Exercise ex){

        Optional<Exercise> tempEx = this.repository.findByName(ex.getName());

        if(tempEx.isPresent()){
            throw new ExerciseAlreadyExistsException();
        }

        return this.repository.save(ex);
    }

    @Transactional
    public void deleteExercise(int id){
        this.repository.deleteById(id);
    }

    public Optional<Exercise> findExerciseByName(String name){
        return this.repository.findByName(name);
    }

    public Optional<Exercise> findExerciseById(int id){
        return this.findExerciseById(id);
    }


}

