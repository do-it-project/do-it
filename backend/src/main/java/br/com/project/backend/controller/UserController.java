package br.com.project.backend.controller;

import br.com.project.backend.DTO.ConfirmResetPasswordDTO;
import br.com.project.backend.DTO.LoginRequestDTO;
import br.com.project.backend.DTO.LoginResponseDTO;
import br.com.project.backend.DTO.RequestResetPasswordDTO;
import br.com.project.backend.utils.EmailUtil;
import br.com.project.backend.model.User;
import br.com.project.backend.security.Token;
import br.com.project.backend.service.TokenResetService;
import br.com.project.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final EmailUtil emailUtil;
    private final TokenResetService tokenResetService;

    public UserController(UserService userService, EmailUtil emailUtil, TokenResetService tokenResetService){
        this.userService = userService;
        this.emailUtil = emailUtil;
        this.tokenResetService = tokenResetService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.status(200).body(userService.usersList());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user ){
        userService.createUser(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {

        Optional<User> tempUser = userService.findUserById(user.getId());

        if (tempUser.isPresent()) {
            String password = tempUser.get().getPassword();

            User updatedUser = userService.editUser(user, password);

            return ResponseEntity.status(200).body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {

        Optional<User> tempUser = userService.findUserById(id);

        if (tempUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authUser(@Valid @RequestBody LoginRequestDTO user) {

        Optional<User> tempUser = userService.findUserByEmail(user.getEmail());

        if (tempUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Boolean valid = userService.authPassword(tempUser.get(), user.getPassword());

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Token token = userService.createToken(tempUser.get());

        return ResponseEntity.ok(generateLoginResponse(tempUser.get(), token));
    }


    @PostMapping("/request-reset-password")
    public ResponseEntity<?> requestReset(@Valid @RequestBody RequestResetPasswordDTO requestResetPassword){

        Optional<User> tempUser = userService.findUserByEmail(requestResetPassword.getEmail());

        if(tempUser.isPresent()){
            // cria o token e relaciona com o email no banco
            tokenResetService.createToken();
            // seta o timer
        }

        return ResponseEntity.ok("");
    }

    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> confirmReset(@Valid @RequestBody ConfirmResetPasswordDTO confirmResetPasswordDTO){



        return ResponseEntity.ok("");
    }

    public LoginResponseDTO generateLoginResponse(User user, Token token){
        LoginResponseDTO response = new LoginResponseDTO();

        response.setPassword(user.getPassword());
        response.setName(user.getName());
        response.setPhone(user.getPhone());
        response.setId(user.getId());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setUrl_photo(user.getUrl_photo());

        response.setToken(token.getToken());

        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        if(e.getMessage().contains("Enum")){
            return ResponseEntity.status(400).body("error: Invalid role");
        }else{
            return ResponseEntity.status(400).body("error: Invalid format JSON");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
