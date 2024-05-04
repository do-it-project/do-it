package br.com.project.backend.utils;

import java.util.Random;

public class TokenResetUtils {

    private final int tokenSize = 6;

    public String generateToken(){
        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(tokenSize);

        for (int i = 0; i < tokenSize; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
