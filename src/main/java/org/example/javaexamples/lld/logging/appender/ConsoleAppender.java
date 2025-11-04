package org.example.javaexamples.lld.logging.appender;

import lombok.RequiredArgsConstructor;
import org.example.javaexamples.lld.logging.formatter.LogFormatter;
import org.example.javaexamples.lld.logging.models.LogMessage;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {
    private final LogFormatter logFormatter;
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logFormatter.format(logMessage));
    }
}
