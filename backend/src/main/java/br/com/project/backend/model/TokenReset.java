package br.com.project.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tokenReset")
public class TokenReset {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="token")
    private String token;

    @Column(name="creation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

    @Column(name="expiration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

    @Column(name = "status")
    private boolean status;

}
