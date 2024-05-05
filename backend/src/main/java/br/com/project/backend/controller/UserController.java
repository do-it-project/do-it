package br.com.project.backend.controller;

import br.com.project.backend.DTO.ConfirmResetPasswordDTO;
import br.com.project.backend.DTO.LoginRequestDTO;
import br.com.project.backend.DTO.LoginResponseDTO;
import br.com.project.backend.DTO.RequestResetPasswordDTO;
import br.com.project.backend.model.TokenReset;
import br.com.project.backend.utils.EmailUtils;
import br.com.project.backend.model.User;
import br.com.project.backend.security.Token;
import br.com.project.backend.service.TokenResetService;
import br.com.project.backend.service.UserService;
import br.com.project.backend.utils.HashUtils;
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
    private final EmailUtils emailUtils;
    private final HashUtils hashUtils;
    private final TokenResetService tokenResetService;

    public UserController(UserService userService, EmailUtils emailUtils, TokenResetService tokenResetService, HashUtils hashUtils){
        this.userService = userService;
        this.emailUtils = emailUtils;
        this.tokenResetService = tokenResetService;
        this.hashUtils = hashUtils;
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
            String currentPasswordHashed = tempUser.get().getPassword();

            User updatedUser = userService.editUser(user, currentPasswordHashed);

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
        System.out.println("entrei");
        if (tempUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        Boolean valid = hashUtils.authStringHash(user.getPassword(), tempUser.get().getPassword());

        if (!valid) {
            System.out.println("errou senha");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Token token = userService.createToken(tempUser.get());

        return ResponseEntity.ok(generateLoginResponse(tempUser.get(), token));
    }


    @PostMapping("/request-reset-password")
    public ResponseEntity<?> requestReset(@Valid @RequestBody RequestResetPasswordDTO requestResetPassword){

        Optional<User> tempUser = userService.findUserByEmail(requestResetPassword.getEmail());

        if(tempUser.isPresent()){
            TokenReset tokenReset = tokenResetService.createToken(tempUser.get());
            emailUtils.sendEmailTokenRequest(tempUser.get(), tokenReset);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok("Token sent successfully.");
    }

    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> confirmReset(@Valid @RequestBody ConfirmResetPasswordDTO confirmResetPassword){

        Optional<TokenReset> tempTokenReset = tokenResetService.findByToken(confirmResetPassword.getToken());

        if(tempTokenReset.isPresent()){

            if(!tokenResetService.isTokenResetValid(tempTokenReset.get())){
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Boolean valid = hashUtils.authStringHash(tempTokenReset.get().getUser().getEmail(), confirmResetPassword.getEmail());
            if(!valid) {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            userService.changePassword(tempTokenReset.get().getUser(), confirmResetPassword.getPassword());
            tokenResetService.attTokenUsed(tempTokenReset.get());
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok("Password modified successfully");

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
