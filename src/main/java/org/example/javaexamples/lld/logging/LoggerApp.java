package org.example.javaexamples.lld.logging;


import org.example.javaexamples.lld.logging.appender.ConsoleAppender;
import org.example.javaexamples.lld.logging.appender.FileAppender;
import org.example.javaexamples.lld.logging.formatter.PlainTextFormatter;
import org.example.javaexamples.lld.logging.models.LogLevel;

public class LoggerApp {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        LogConfiguration.addLogAppender(new ConsoleAppender(new PlainTextFormatter()), LogLevel.DEBUG);
        LogConfiguration.addLogAppender(new ConsoleAppender(new PlainTextFormatter()), LogLevel.INFO);
        LogConfiguration.addLogAppender(new ConsoleAppender(new PlainTextFormatter()), LogLevel.ERROR);
        LogConfiguration.addLogAppender(new FileAppender(new PlainTextFormatter(), "log_file.txt"), LogLevel.INFO);
        LogConfiguration.addLogAppender(new FileAppender(new PlainTextFormatter(), "log_file.txt"), LogLevel.ERROR);

        logger.log(LogLevel.DEBUG,"Hello How are you");
        logger.log(LogLevel.INFO,"Hello I m fine");
        logger.log(LogLevel.ERROR,"404 Error");
    }
}
