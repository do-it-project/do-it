package br.com.project.backend.repository;

import br.com.project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
