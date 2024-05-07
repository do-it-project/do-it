package br.com.project.backend.DTO.request;

import br.com.project.backend.DTO.entities.UserDTO;
import lombok.Data;

@Data
public class EditUserRequestDTO extends UserDTO {
    private String password;
}
