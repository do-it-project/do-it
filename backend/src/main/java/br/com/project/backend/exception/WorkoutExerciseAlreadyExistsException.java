package br.com.project.backend.exception;

public class WorkoutExerciseAlreadyExistsException extends RuntimeException{

    public WorkoutExerciseAlreadyExistsException() {
        super("Exercise duplicated trying been added to workout");
    }

    public WorkoutExerciseAlreadyExistsException(String message) {
        super(message);
    }
}

