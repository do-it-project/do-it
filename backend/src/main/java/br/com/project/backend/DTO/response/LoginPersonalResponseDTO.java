package br.com.project.backend.DTO.response;

import br.com.project.backend.DTO.entities.PersonalDTO;
import br.com.project.backend.security.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPersonalResponseDTO {
    private PersonalDTO user;
    private Token token;
}
