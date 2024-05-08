package br.com.project.backend.exception;

public class WorkoutAlreadyExistsException extends RuntimeException{
    public WorkoutAlreadyExistsException() {
        super("Workout already exists with this name");
    }

    public WorkoutAlreadyExistsException(String message) {
        super(message);
    }
}
