package br.com.project.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="token_reset")
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

    @Column(name = "used", nullable = false)
    private boolean used;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TokenReset(){
        int tokenSize = 6;

        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(tokenSize);

        for (int i = 0; i < tokenSize; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        
        this.token = sb.toString();
    }

    public boolean getUsed(){
        return this.used;
    }

}
