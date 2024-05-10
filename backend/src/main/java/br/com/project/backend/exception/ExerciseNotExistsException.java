package br.com.project.backend.exception;

public class ExerciseNotExistsException extends RuntimeException{
    public ExerciseNotExistsException() {
        super("Exercise not exists");
    }

    public ExerciseNotExistsException(String message) {
        super(message);
    }
}
