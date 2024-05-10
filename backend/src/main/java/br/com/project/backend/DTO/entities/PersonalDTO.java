package br.com.project.backend.DTO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String url_photo;
    private String type;
}
