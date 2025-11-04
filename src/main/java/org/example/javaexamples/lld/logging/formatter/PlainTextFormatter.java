package org.example.javaexamples.lld.logging.formatter;

import org.example.javaexamples.lld.logging.models.LogMessage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PlainTextFormatter implements LogFormatter{
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public String format(LogMessage logMessage) {
        String formattedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(logMessage.getTimeStamp()), ZoneId.systemDefault()).format(dateTimeFormatter);
        return String.format("%s [%s] - %s", formattedTime,logMessage.getLevel(),logMessage.getMessage());
    }
}
