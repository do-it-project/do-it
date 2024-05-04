package br.com.project.backend.service;

import br.com.project.backend.model.User;
import br.com.project.backend.model.UserRoles;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUser repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUser repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> usersList(){
        return this.repository.findAll();
    }


    public User createUser(User user){

        return this.repository.save(user);

        String encoder = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(encoder);
        return this.repository.save(user);
    }

    public User editUser(User user, String password) {

        if (!(password.equals(user.getPassword()))) {
            String encodePassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }

        return this.repository.save(user);
    }

    public void deleteUser(int id) {
        this.repository.deleteById(id);
    }

    public Optional<User> findUserById(int id){
        return this.repository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return this.repository.findByEmail(email);
    }

    public Boolean authPassword(User user, String password) {
        Boolean valid = passwordEncoder.matches(password, user.getPassword());

        return valid;

    public void createUser(User user){
        this.repository.save(user);
    }

    public Token createToken(User user){
        return new Token(TokenUtil.createToken(user));
    }

}
