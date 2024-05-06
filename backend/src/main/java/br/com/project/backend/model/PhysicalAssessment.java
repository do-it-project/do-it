package br.com.project.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao_fisica")
public class PhysicalAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "comments", length = 500)
    private String comments;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "fat_percentage", nullable = false)
    private double fat_percentage;

    @Column(name = "water_percentage", nullable = false)
    private double water_percentage;

    @Column(name = "imc", nullable = false)
    private double imc;

    @Column(name = "waist_measurement", nullable = false)
    private double waist_measurement;

    @Column(name = "right_arm_measurement", nullable = false)
    private double right_arm_measurement;

    @Column(name = "left_arm_measurement", nullable = false)
    private double left_arm_measurement;

    @Column(name = "left_leg_measurement", nullable = false)
    private double left_leg_measurement;

    @Column(name = "right_leg_measurement", nullable = false)
    private double right_leg_measurement;

    @Column(name = "right_calf_measurement", nullable = false)
    private double right_calf_measurement;

    @Column(name = "left_calf_measurement", nullable = false)
    private double left_calf_measurement;

    @Column(name = "chest_measurement", nullable = false)
    private double chest_measurement;

    @Column(name = "abdominal_measurement", nullable = false)
    private double abdominal_measurement;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PhysicalAssessment(){
        calc_imc();
    }

    public void calc_imc(){
        this.imc =  this.weight / (this.height * this.height);
    }
}
