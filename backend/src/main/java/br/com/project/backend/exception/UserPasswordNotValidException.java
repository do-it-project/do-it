package br.com.project.backend.exception;


public class UserPasswordNotValidException extends RuntimeException{
    public UserPasswordNotValidException(){
        super("Password must be at least 5 characters long");
    }

    public UserPasswordNotValidException(String message){
        super(message);
    }
}
