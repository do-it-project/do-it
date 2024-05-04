package br.com.project.backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public LocalDateTime formatDate(LocalDateTime dataReceived){
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormated = dataReceived.format(datePattern);

        return LocalDateTime.parse(dateFormated, datePattern);
    }
}
