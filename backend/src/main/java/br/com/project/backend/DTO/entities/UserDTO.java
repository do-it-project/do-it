package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String url_photo;

    private UserRoles role;
}
