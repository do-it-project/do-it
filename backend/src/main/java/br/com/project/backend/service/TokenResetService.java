package br.com.project.backend.service;

import br.com.project.backend.model.TokenReset;
import br.com.project.backend.model.User;
import br.com.project.backend.repository.ITokenReset;
import br.com.project.backend.utils.DateUtils;
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

    public TokenReset createToken(User user){
        LocalDateTime creationDate = LocalDateTime.now();

        TokenReset tokenReset = new TokenReset();

        tokenReset.setCreationDate(new DateUtils().formatDate(creationDate));
        tokenReset.setExpirationDate(new DateUtils().formatDate(creationDate.plusMinutes(10)));
        tokenReset.setUsed(false);
        tokenReset.setUser(user);

        return this.repository.save(tokenReset);
    }

    public TokenReset attTokenUsed(TokenReset tokenReset){
        tokenReset.setUsed(true);
        return this.repository.save(tokenReset);
    }

    public boolean isTokenResetValid(TokenReset tokenReset){
        if(tokenReset.getUsed()) {
            return false;
        }

        LocalDateTime tempTimeNow = LocalDateTime.now();

        if(tempTimeNow.isAfter(tokenReset.getExpirationDate())){
            return false;
        }

        return true;
    }

    public Optional<TokenReset> findByToken(String token){
        return this.repository.findByToken(token);
    }

}
