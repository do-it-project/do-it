package br.com.project.backend.repository;

import br.com.project.backend.model.Personal;
import br.com.project.backend.model.Student;
import br.com.project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUser extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    Student findStudentByEmail(String email);

    Student findStudentById(Integer id);

    Personal findPersonalByEmail(String email);

    List<Student> findStudentsByType(String type);

    List<Personal> findPersonalsByType(String type);

}
