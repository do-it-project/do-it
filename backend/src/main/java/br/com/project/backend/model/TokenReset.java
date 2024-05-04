package br.com.project.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tokenReset")
public class TokenReset {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="token", nullable = false)
    private String token;

    @Column(name="creation_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime creationDate;

    @Column(name="expiration_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public boolean getStatus(){
        return this.status;
    }

}
