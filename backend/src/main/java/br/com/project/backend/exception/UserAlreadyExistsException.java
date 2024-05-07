package br.com.project.backend.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(){
        super("A user already exists with this email");
    }

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
