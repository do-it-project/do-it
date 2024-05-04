package br.com.project.backend.service;

import br.com.project.backend.model.TokenReset;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.ITokenReset;
import br.com.project.backend.repository.IUser;
import br.com.project.backend.security.Token;
import br.com.project.backend.security.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        return this.repository.save(tokenReset);
    }

    public Optional<TokenReset> findByToken(String token){
        return this.repository.findByToken(token);
    }

    // public expire
}
