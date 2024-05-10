package br.com.project.backend.DTO.response;

import br.com.project.backend.DTO.entities.StudentDTO;
import br.com.project.backend.security.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginStudentResponseDTO {
    private StudentDTO user;
    private Token token;
}
