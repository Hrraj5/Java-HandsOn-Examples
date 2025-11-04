package org.example.javaexamples.lld.logging.appender;

import org.example.javaexamples.lld.logging.models.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
