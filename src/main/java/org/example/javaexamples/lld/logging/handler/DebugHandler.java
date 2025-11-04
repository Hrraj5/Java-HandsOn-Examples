package org.example.javaexamples.lld.logging.handler;

import org.example.javaexamples.lld.logging.models.LogLevel;
import org.example.javaexamples.lld.logging.models.LogMessage;

public class DebugHandler extends LogHandler{
    @Override
    public boolean canHandle(LogMessage logMessage) {
        return (LogLevel.DEBUG == logMessage.getLevel());
    }
}
