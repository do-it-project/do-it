package br.com.project.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class HashUtils {

    private final String SALT = "doit.vr.project";
    private final PasswordEncoder passwordEncoder;

    public HashUtils(){
        this.passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom(SALT.getBytes()));
    }

    public String hashString(String input) {
        return passwordEncoder.encode(input);
    }

    public Boolean authStringHash(String inputText, String inputHashed){
        return passwordEncoder.matches(inputText, inputHashed);
    }
}
