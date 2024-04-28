package br.com.project.backend.service;

import br.com.project.backend.model.User;
import br.com.project.backend.model.UserRoles;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUser repository;

    public UserService(IUser repository){
        this.repository = repository;
    }

    public List<User> usersList(){
        return this.repository.findAll();
    }

    public void createUser(User user){
        this.repository.save(user);
    }

    public Token createToken(User user){
        return new Token(TokenUtil.createToken(user));
    }

}
