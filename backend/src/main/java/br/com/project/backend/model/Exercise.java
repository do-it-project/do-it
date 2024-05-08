package br.com.project.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
