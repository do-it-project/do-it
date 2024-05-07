package br.com.project.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        Map<String, String> errors = new HashMap<>();

        if(e.getMessage().contains("Enum")){
            errors.put("error", "Invalid role");
        }else{
            errors.put("error", "Invalid format JSON");
        }

        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(DataIntegrityViolationException e){
        Map<String, String> errors = new HashMap<>();

        if(e.getMessage().contains("into user")){
            errors.put("error", "Email for user already used");
        }else if(e.getMessage().contains("into avaliacao_fisica")){
            errors.put("error", "Name for avaliacao_fisica already used");
        }
        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
