package br.com.project.backend.service;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.exception.UserAlreadyExistsException;
import br.com.project.backend.exception.UserPasswordNotValidException;
import br.com.project.backend.mapper.UserMapper;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
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

        return userMapper.toDTO(this.repository.save(user));
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

        return userMapper.toDTO(this.repository.save(user));
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

    public Optional<User> findUserById(int id){
        return this.repository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return this.repository.findByEmail(email);
    }

    public Token createToken(User user){
        return new Token(TokenUtil.createToken(user));
    }

}
