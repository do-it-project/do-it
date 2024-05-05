package br.com.project.backend.service;

import br.com.project.backend.model.User;
import br.com.project.backend.model.UserRoles;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import br.com.project.backend.utils.HashUtils;
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
    private final HashUtils hashUtils;

    public UserService(IUser repository, HashUtils hashUtils){
        this.repository = repository;
        this.hashUtils = hashUtils;
    }

    public List<User> usersList(){
        return this.repository.findAll();
    }

    public User createUser(User user){
        String encoder = hashUtils.hashString(user.getPassword());

        user.setPassword(encoder);
        return this.repository.save(user);
    }

    public User editUser(User user, String passwordHashed) {

        if (!(passwordHashed.equals(user.getPassword()))) {
            String encodePassword = hashUtils.hashString(user.getPassword());
            user.setPassword(encodePassword);
        }

        return this.repository.save(user);
    }

    public void deleteUser(int id) {
        this.repository.deleteById(id);
    }

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
