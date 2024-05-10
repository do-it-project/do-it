package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.PhysicalAssessment;
import br.com.project.backend.model.Workout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private char type;
}
