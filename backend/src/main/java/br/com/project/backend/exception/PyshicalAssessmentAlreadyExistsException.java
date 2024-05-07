package br.com.project.backend.exception;

public class PyshicalAssessmentAlreadyExistsException extends RuntimeException {

    public PyshicalAssessmentAlreadyExistsException(){
        super("A physical assessment already exists with this name");
    }

    public PyshicalAssessmentAlreadyExistsException(String message){
        super(message);
    }
}
