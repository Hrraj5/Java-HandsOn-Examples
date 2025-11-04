package org.example.javaexamples.lld.logging;

import org.example.javaexamples.lld.logging.handler.LogHandler;
import org.example.javaexamples.lld.logging.models.LogLevel;
import org.example.javaexamples.lld.logging.models.LogMessage;

public class Logger {
    private final static Logger INSTANCE = new Logger();
    private final LogHandler logHandler;
    private Logger(){
        logHandler = LogConfiguration.build();
    }

    public static Logger getInstance(){
        return INSTANCE;
    }

    public void log(LogLevel logLevel, String message){
        LogMessage logMessage = new LogMessage(message,logLevel,System.currentTimeMillis());
        logHandler.handle(logMessage);
    }

    public void warn(String message){

    }

    public void info(String message){

    }

    public void error(String message){
        log(LogLevel.WARN,message);
    }
}
