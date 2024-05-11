package br.com.project.backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "The name field is required")
    @Column(name="name", length = 60, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "The description field is required")
    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "The link_tutorial field is required")
    @Column(name="link_tutorial", columnDefinition = "TEXT")
    private String link_tutorial;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<WorkoutExercise> workout_exercises;

    public Exercise(String name, String description, String link_tutorial) {
        this.name = name;
        this.description = description;
        this.link_tutorial = link_tutorial;
    }
}
