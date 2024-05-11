package br.com.project.backend.controller;

import br.com.project.backend.DTO.entities.PersonalDTO;
import br.com.project.backend.DTO.entities.StudentDTO;
import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.DTO.request.ConfirmResetPasswordRequestDTO;
import br.com.project.backend.DTO.request.EditUserRequestDTO;
import br.com.project.backend.DTO.request.LoginRequestDTO;
import br.com.project.backend.DTO.request.ResetPasswordRequestDTO;
import br.com.project.backend.DTO.response.LoginPersonalResponseDTO;
import br.com.project.backend.DTO.response.LoginStudentResponseDTO;
import br.com.project.backend.mapper.UserMapper;
import br.com.project.backend.model.TokenReset;
import br.com.project.backend.utils.EmailUtils;
import br.com.project.backend.model.User;
import br.com.project.backend.security.Token;
import br.com.project.backend.service.TokenResetService;
import br.com.project.backend.service.UserService;
import br.com.project.backend.utils.HashUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private HashUtils hashUtils;

    @Autowired
    private TokenResetService tokenResetService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.status(200).body(userService.usersList());
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents(){
        return ResponseEntity.status(200).body(userService.studentsList());
    }

    @GetMapping("/personals")
    public ResponseEntity<List<PersonalDTO>> getPersonals(){
        return ResponseEntity.status(200).body(userService.personalsList());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user){
        UserDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<UserDTO> editUser(@Valid @RequestBody EditUserRequestDTO user) {

        Optional<User> tempUser = userService.findUserById(user.getId());

        if (tempUser.isPresent()) {
            UserDTO updatedUser = userService.editUser(user, tempUser.get());

            return ResponseEntity.status(200).body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

        Optional<User> tempUser = userService.findUserById(id);

        if (tempUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequestDTO user) {

        Optional<User> tempUser = userService.findUserByEmail(user.getEmail());

        if (tempUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Boolean valid = hashUtils.authStringHash(user.getPassword(), tempUser.get().getPassword());

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Token token = userService.createToken(tempUser.get());

        if(tempUser.get().getType().equals("S")){
            LoginStudentResponseDTO loginResponse = this.userService.doStudentLogin(tempUser.get().getEmail(), token);
            return ResponseEntity.ok(loginResponse);

        } else if (tempUser.get().getType().equals("P")) {
            LoginPersonalResponseDTO loginResponse = this.userService.doPersonalLogin(tempUser.get().getEmail(), token);
            return ResponseEntity.ok(loginResponse);

        }else{
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/request-reset-password")
    public ResponseEntity<?> requestReset(@Valid @RequestBody ResetPasswordRequestDTO requestResetPassword){

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
    public ResponseEntity<?> confirmReset(@Valid @RequestBody ConfirmResetPasswordRequestDTO confirmResetPassword){

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
}
