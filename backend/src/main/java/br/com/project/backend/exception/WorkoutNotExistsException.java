package br.com.project.backend.exception;

public class WorkoutNotExistsException extends RuntimeException{
    public WorkoutNotExistsException() {
        super("Workout not exists");
    }

    public WorkoutNotExistsException(String message) {
        super(message);
    }
}
