package br.com.project.backend.repository;

import br.com.project.backend.model.TokenReset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ITokenReset extends JpaRepository<TokenReset, Integer> {
    Optional<TokenReset> findByToken(String token);
}
