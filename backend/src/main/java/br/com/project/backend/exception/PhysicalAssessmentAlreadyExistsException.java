package br.com.project.backend.exception;

public class PhysicalAssessmentAlreadyExistsException extends RuntimeException {

    public PhysicalAssessmentAlreadyExistsException(){
        super("A physical assessment already exists with this name");
    }

    public PhysicalAssessmentAlreadyExistsException(String message){
        super(message);
    }
}
