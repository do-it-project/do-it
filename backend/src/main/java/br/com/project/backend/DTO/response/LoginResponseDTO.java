package br.com.project.backend.DTO.response;

import br.com.project.backend.DTO.entities.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO extends UserDTO {
    private String token;
}
