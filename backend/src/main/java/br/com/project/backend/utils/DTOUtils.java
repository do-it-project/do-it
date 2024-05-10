package br.com.project.backend.utils;

import br.com.project.backend.DTO.entities.PersonalDTO;
import br.com.project.backend.DTO.entities.StudentDTO;
import br.com.project.backend.DTO.response.LoginPersonalResponseDTO;
import br.com.project.backend.DTO.response.LoginStudentResponseDTO;
import br.com.project.backend.security.Token;
import org.springframework.stereotype.Service;

@Service
public class DTOUtils {

    public LoginStudentResponseDTO loginStudent(StudentDTO student, Token token) {
        LoginStudentResponseDTO response = new LoginStudentResponseDTO();
        response.setUser(student);
        response.setToken(token);

        return response;
    }

    public LoginPersonalResponseDTO loginPersonal(PersonalDTO personal, Token token) {
        LoginPersonalResponseDTO response = new LoginPersonalResponseDTO();
        response.setUser(personal);
        response.setToken(token);

        return response;
    }
}
