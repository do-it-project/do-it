package br.com.project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "The name field is required")
    @Column(name="name", length = 60, nullable = false, unique = true)
    private String name;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="link_tutorial", columnDefinition = "TEXT")
    private String link_tutorial;
}
