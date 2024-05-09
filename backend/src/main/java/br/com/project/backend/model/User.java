package br.com.project.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.EnumMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("U")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "The name field is required")
    @Column(name="name", length = 60, nullable = false)
    private String name;

    @NotBlank(message = "The email field is required")
    @Email(message = "Email is not valid")
    @Column(name="email", length = 60, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "The phone field is required")
    @Column(name="phone", length = 15, nullable = false)
    private String phone;

    @NotBlank(message = "The password field is required")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(name="url_photo", columnDefinition = "TEXT")
    private String url_photo;

    @Column(insertable = false, updatable = false)
    private char type;
}

