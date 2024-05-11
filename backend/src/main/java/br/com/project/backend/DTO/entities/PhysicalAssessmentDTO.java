package br.com.project.backend.DTO.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhysicalAssessmentDTO {
    private int id;
    private String name;
    private LocalDateTime creationDate;
    private String comments;
    private Double height;
    private Double weight;
    private Double fat_percentage;
    private Double water_percentage;
    private Double imc;
    private Double waist_measurement;
    private Double right_arm_measurement;
    private Double left_arm_measurement;
    private Double left_leg_measurement;
    private Double right_leg_measurement;
    private Double right_calf_measurement;
    private Double left_calf_measurement;
    private Double chest_measurement;
    private Double abdominal_measurement;
    private String student_name;
    private String student_email;
}
