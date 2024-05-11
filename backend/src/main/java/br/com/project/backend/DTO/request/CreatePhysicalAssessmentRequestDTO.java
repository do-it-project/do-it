package br.com.project.backend.DTO.request;

import br.com.project.backend.model.Student;
import br.com.project.backend.utils.DTOUtils;
import br.com.project.backend.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhysicalAssessmentRequestDTO {

    @NotBlank(message = "The name field is required")
    private String name;

    private LocalDateTime creationDate;

    private String comments;

    @NotNull(message = "The height field is required")
    private Double height;

    @NotNull(message = "The weight field is required")
    private Double weight;

    @NotNull(message = "The fat_percentage field is required")
    private Double fat_percentage;

    @NotNull(message = "The water_percentage field is required")
    private Double water_percentage;

    private Double imc;

    @NotNull(message = "The waist_measurement field is required")
    private Double waist_measurement;

    @NotNull(message = "The right_arm_measurement field is required")
    private Double right_arm_measurement;

    @NotNull(message = "The left_arm_measurement field is required")
    private Double left_arm_measurement;

    @NotNull(message = "The left_leg_measurement field is required")
    private Double left_leg_measurement;

    @NotNull(message = "The right_leg_measurement field is required")
    private Double right_leg_measurement;

    @NotNull(message = "The right_calf_measurement field is required")
    private Double right_calf_measurement;

    @NotNull(message = "The left_calf_measurement field is required")
    private Double left_calf_measurement;

    @NotNull(message = "The chest_measurement field is required")
    private Double chest_measurement;

    @NotNull(message = "The abdominal_measurement field is required")
    private Double abdominal_measurement;

    @NotNull(message = "The id_student field is required")
    private Integer id_student;

    public Double calc_imc(){
        if(this.height != null)
            return this.imc =  this.weight / (this.height * this.height);
        return 0.0;
    }
}
