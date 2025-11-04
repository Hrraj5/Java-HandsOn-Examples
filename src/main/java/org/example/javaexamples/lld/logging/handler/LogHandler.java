package org.example.javaexamples.lld.logging.handler;

import lombok.Setter;
import org.example.javaexamples.lld.logging.appender.LogAppender;
import org.example.javaexamples.lld.logging.models.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {

    @Setter
    private LogHandler next;

    private final List<LogAppender> appenderList = new CopyOnWriteArrayList<>();

    public void subscribe(LogAppender logAppender){
        appenderList.add(logAppender);
    }

    public void notifySubScriber(LogMessage logMessage){
        for(LogAppender appender: appenderList){
            appender.append(logMessage);
        }
    }

    public void handle(LogMessage logMessage){
        if(canHandle(logMessage)){
            notifySubScriber(logMessage);
        }else{
            if(next!=null){
                next.handle(logMessage);
            }
        }
    }

    public abstract boolean canHandle(LogMessage logMessage);

}
