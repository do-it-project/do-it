package br.com.project.backend.repository;

import br.com.project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUser extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
