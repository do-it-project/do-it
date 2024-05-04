package br.com.project.backend.DTO;

import br.com.project.backend.model.User;
import br.com.project.backend.security.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO extends User {
    private String token;
}
