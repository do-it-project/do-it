package br.com.project.backend.exception;

public class ExerciseAlreadyExistsException extends RuntimeException{

    public ExerciseAlreadyExistsException(){
        super("Exercise already exists!");
    }

    public ExerciseAlreadyExistsException(String message){
        super(message);
    }
}
