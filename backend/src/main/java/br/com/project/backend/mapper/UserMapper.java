package br.com.project.backend.mapper;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    List<UserDTO> toDTOList(List<User> userList);
}
