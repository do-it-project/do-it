package br.com.project.backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "The name field is required")
    @Column(name = "name",length = 60, nullable = false, unique = true)
    private String name;

    @NotBlank(message = "The comments field is required")
    @Column(name = "comments", columnDefinition = "TEXT", nullable = false)
    private String comments;

    @Column(name="creation_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Student student;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<WorkoutExercise> workout_exercises;

    public Workout(String name, String comments, Student student, LocalDateTime creation_date) {
        this.name = name;
        this.comments = comments;
        this.student = student;
        this.creationDate = creation_date;
    }
}
