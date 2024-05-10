package br.com.project.backend.DTO.entities;

import br.com.project.backend.model.PhysicalAssessment;
import br.com.project.backend.model.Workout;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String url_photo;
    private String type;
    private List<PhysicalAssessment> physicalAssessments;
    private List<Workout> workouts;
}
