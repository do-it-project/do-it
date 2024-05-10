package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.PersonalDTO;
import br.com.project.backend.DTO.entities.StudentDTO;
import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.model.Personal;
import br.com.project.backend.model.Student;
import br.com.project.backend.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO UserToUserDTO(User user);

    StudentDTO StudentToStudentDTO(Student student);

    Personal UserToPersonal(User user);

    Student UserToStudent(User user);

    UserDTO StudentToUserDTO(Student student);

    PersonalDTO PersonalToPersonalDTO(Personal personal);

    List<UserDTO> toDTOList(List<User> userList);
}
