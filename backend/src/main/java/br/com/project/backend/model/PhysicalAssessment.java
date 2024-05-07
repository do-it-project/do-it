package br.com.project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao_fisica")
public class PhysicalAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "The name field is required")
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @NotBlank(message = "The comments field is required")
    @Column(name = "comments", length = 500)
    private String comments;

    @NotNull(message = "The height field is required")
    @Column(name = "height", nullable = false)
    private Double height;

    @NotNull(message = "The weight field is required")
    @Column(name = "weight", nullable = false)
    private Double weight;

    @NotNull(message = "The fat_percentage field is required")
    @Column(name = "fat_percentage", nullable = false)
    private Double fat_percentage;

    @NotNull(message = "The water_percentage field is required")
    @Column(name = "water_percentage", nullable = false)
    private Double water_percentage;

    @NotNull(message = "The imc field is required")
    @Column(name = "imc", nullable = false)
    private Double imc;

    @NotNull(message = "The waist_measurement field is required")
    @Column(name = "waist_measurement", nullable = false)
    private Double waist_measurement;

    @NotNull(message = "The right_arm_measurement field is required")
    @Column(name = "right_arm_measurement", nullable = false)
    private Double right_arm_measurement;

    @NotNull(message = "The left_arm_measurement field is required")
    @Column(name = "left_arm_measurement", nullable = false)
    private Double left_arm_measurement;

    @NotNull(message = "The left_leg_measurement field is required")
    @Column(name = "left_leg_measurement", nullable = false)
    private Double left_leg_measurement;

    @NotNull(message = "The right_leg_measurement field is required")
    @Column(name = "right_leg_measurement", nullable = false)
    private Double right_leg_measurement;

    @NotNull(message = "The right_calf_measurement field is required")
    @Column(name = "right_calf_measurement", nullable = false)
    private Double right_calf_measurement;

    @NotNull(message = "The left_calf_measurement field is required")
    @Column(name = "left_calf_measurement", nullable = false)
    private Double left_calf_measurement;

    @NotNull(message = "The chest_measurement field is required")
    @Column(name = "chest_measurement", nullable = false)
    private Double chest_measurement;

    @NotNull(message = "The abdominal_measurement field is required")
    @Column(name = "abdominal_measurement", nullable = false)
    private Double abdominal_measurement;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PhysicalAssessment(){
        calc_imc();
    }

    public void calc_imc(){
        if(this.height != null)
            this.imc =  this.weight / (this.height * this.height);
    }
}
