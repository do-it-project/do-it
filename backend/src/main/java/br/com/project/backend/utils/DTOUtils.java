package br.com.project.backend.utils;

import br.com.project.backend.DTO.entities.UserDTO;
import br.com.project.backend.DTO.response.LoginResponseDTO;
import br.com.project.backend.model.User;
import br.com.project.backend.security.Token;

public class DTOUtils {

    // Generate userDTO response to deprive sensitive data
    public UserDTO generateUser(User user){
        UserDTO response = new UserDTO();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setPhone(user.getPhone());
        response.setId(user.getId());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setUrl_photo(user.getUrl_photo());

        return response;
    }

    // Generate loginResponseDTO response to deprive sensitive data
    public LoginResponseDTO generateLoginResponse(User user, Token token){
        LoginResponseDTO response = new LoginResponseDTO();

        response.setName(user.getName());
        response.setPhone(user.getPhone());
        response.setId(user.getId());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());
        response.setUrl_photo(user.getUrl_photo());

        response.setToken(token.getToken());

        return response;
    }
}
