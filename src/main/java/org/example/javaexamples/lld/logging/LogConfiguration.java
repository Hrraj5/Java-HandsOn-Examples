package org.example.javaexamples.lld.logging;

import org.example.javaexamples.lld.logging.appender.LogAppender;
import org.example.javaexamples.lld.logging.handler.*;
import org.example.javaexamples.lld.logging.models.LogLevel;

public class LogConfiguration {

    private static final LogHandler debugHandler = new DebugHandler();
    private static final LogHandler warnHandler = new WarnHandler();
    private static final LogHandler infoHandler  = new InfoHandler();
    private static final LogHandler errorHandler = new ErrorHandler();

    public static LogHandler build(){
        debugHandler.setNext(infoHandler);
        infoHandler.setNext(warnHandler);
        warnHandler.setNext(errorHandler);
        return debugHandler;
    }

    public static void addLogAppender(LogAppender logAppender, LogLevel logLevel){
        switch (logLevel){
            case DEBUG -> debugHandler.subscribe(logAppender);
            case INFO -> infoHandler.subscribe(logAppender);
            case WARN -> warnHandler.subscribe(logAppender);
            case ERROR -> errorHandler.subscribe(logAppender);
        }
    }
}
