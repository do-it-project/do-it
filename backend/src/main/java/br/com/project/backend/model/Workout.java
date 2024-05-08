package br.com.project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
