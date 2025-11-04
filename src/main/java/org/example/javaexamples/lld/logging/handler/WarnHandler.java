package org.example.javaexamples.lld.logging.handler;

import org.example.javaexamples.lld.logging.models.LogLevel;
import org.example.javaexamples.lld.logging.models.LogMessage;

public class WarnHandler extends LogHandler{
    @Override
    public boolean canHandle(LogMessage logMessage) {
        return LogLevel.WARN == logMessage.getLevel();
    }
}
