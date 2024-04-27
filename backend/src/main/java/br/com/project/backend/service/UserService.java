package br.com.project.backend.service;

import br.com.project.backend.model.User;
import br.com.project.backend.repository.IUser;
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

    public User createUser(User user){
        return this.repository.save(user);
    }

}
