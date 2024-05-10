package br.com.project.backend.exception;

public class UserTypeNotExistsException extends RuntimeException {

    public UserTypeNotExistsException(){
        super("Invalid user type");
    }

    public UserTypeNotExistsException(String message){
        super(message);
    }
}
