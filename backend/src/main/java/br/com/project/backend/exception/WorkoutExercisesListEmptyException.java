package br.com.project.backend.exception;

public class WorkoutExercisesListEmptyException extends RuntimeException {

    public WorkoutExercisesListEmptyException() {
        super("Workout exercises list is empty");
    }

    public WorkoutExercisesListEmptyException(String message) {
        super(message);
    }
}
