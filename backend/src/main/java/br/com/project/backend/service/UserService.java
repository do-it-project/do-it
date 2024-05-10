package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.DTO.response.LoginPersonalResponseDTO;
import br.com.project.backend.DTO.response.LoginStudentResponseDTO;
import br.com.project.backend.exception.UserAlreadyExistsException;
import br.com.project.backend.exception.UserPasswordNotValidException;
import br.com.project.backend.exception.UserTypeNotExistsException;
import br.com.project.backend.mapper.UserMapper;
import br.com.project.backend.model.Personal;
import br.com.project.backend.model.Student;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import br.com.project.backend.utils.DTOUtils;
import br.com.project.backend.utils.HashUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private IUser repository;

    @Autowired
    private HashUtils hashUtils;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public List<UserDTO> usersList(){
        List<User> users = this.repository.findAll();

        return userMapper.toDTOList(users);
    }

    @Transactional
    public UserDTO createUser(User user){

        Optional<User> tempUser = this.findUserByEmail(user.getEmail());

        if(tempUser.isPresent()){
            throw new UserAlreadyExistsException();
        }

        String encoder = hashUtils.hashString(user.getPassword());
        user.setPassword(encoder);

        if(user.getType().equals("P")){
            Personal userPersonal = userMapper.UserToPersonal(user);
            return userMapper.UserToUserDTO(this.repository.save(userPersonal));

        }else if (user.getType().equals("S")){
            Student userStudent = userMapper.UserToStudent(user);
            return userMapper.UserToUserDTO(this.repository.save(userStudent));

        } else {
            throw new UserTypeNotExistsException();
        }
    }

    @Transactional
    public UserDTO editUser(String passwordText, User user) {

        if (!passwordText.isEmpty()) {
            if(passwordText.length() >= 5){
                String encodePassword = hashUtils.hashString(passwordText);
                user.setPassword(encodePassword);
            } else {
                throw new UserPasswordNotValidException();
            }
        }

        return userMapper.UserToUserDTO(this.repository.save(user));
    }

    @Transactional
    public void deleteUser(int id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public User changePassword(User user, String newPassword){
        String passwordHash = hashUtils.hashString(newPassword);
        user.setPassword(passwordHash);

        return this.repository.save(user);
    }

    public LoginStudentResponseDTO doStudentLogin(String userEmail, Token token){
        Student tempStudent = this.findStudentByEmail(userEmail);

        return new DTOUtils().loginStudent(userMapper.StudentToStudentDTO(tempStudent), token);
    }

    public LoginPersonalResponseDTO doPersonalLogin(String userEmail, Token token){
        Personal tempPersonal = this.findPersonalByEmail(userEmail);

        return new DTOUtils().loginPersonal(userMapper.PersonalToPersonalDTO(tempPersonal), token);
    }

    public Optional<User> findUserById(int id){
        return this.repository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return this.repository.findUserByEmail(email);
    }

    public Student findStudentByEmail(String email){
        return this.repository.findStudentByEmail(email);
    }

    public Personal findPersonalByEmail(String email){
        return this.repository.findPersonalByEmail(email);
    }

    public Token createToken(User user){
        return new Token(TokenUtil.createToken(user));
    }

}
