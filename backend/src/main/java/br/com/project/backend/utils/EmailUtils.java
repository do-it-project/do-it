package br.com.project.backend.utils;

import br.com.project.backend.model.TokenReset;
import br.com.project.backend.model.User;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    private final HashUtils hashUtils;

    public EmailUtils(HashUtils hashUtils){
        this.hashUtils = hashUtils;
    }

    public void sendEmail(String to, String subject, String body) {
        JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) mailSender;
        mailSenderImpl.getSession().getProperties().put("mail.smtp.starttls.enable", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmailTokenRequest(User user, TokenReset tokenReset){

        String encodedEmail = hashUtils.hashString(user.getEmail());

        this.sendEmail(
                user.getEmail(),
                "Reset Password Token",
                "Hello " + user.getName()
                        + "\nReset link: http://localhost:5173/reset?email=" + encodedEmail
                        + "\nToken: " + tokenReset.getToken()
        );
    }
}
