package br.com.project.backend.service;

import br.com.project.backend.model.TokenReset;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.ITokenReset;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import br.com.project.backend.utils.DateUtils;
import br.com.project.backend.utils.TokenResetUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenResetService {

    private final ITokenReset repository;

    public TokenResetService(ITokenReset repository){
        this.repository = repository;
    }

    public List<TokenReset> tokenResetList(){
        return this.repository.findAll();
    }

    public TokenReset createToken(TokenReset tokenReset){
        LocalDateTime creationDate = LocalDateTime.now();

        tokenReset.setToken(new TokenResetUtils().generateToken());
        tokenReset.setCreationDate(new DateUtils().formatDate(creationDate));
        tokenReset.setExpirationDate(new DateUtils().formatDate(creationDate.plusMinutes(10)));
        tokenReset.setStatus(false);

        return this.repository.save(tokenReset);
    }

    public Optional<TokenReset> findByToken(String token){
        return this.repository.findByToken(token);
    }

    // public expire
}
