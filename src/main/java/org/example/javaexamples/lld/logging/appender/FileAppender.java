package org.example.javaexamples.lld.logging.appender;

import org.example.javaexamples.lld.logging.formatter.LogFormatter;
import org.example.javaexamples.lld.logging.models.LogMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileAppender implements LogAppender {

    private final LogFormatter logFormatter;
    private final BufferedWriter writer;

    public FileAppender(LogFormatter logFormatter, String fileName){
        this.logFormatter = logFormatter;
        try{
            this.writer = new BufferedWriter(new FileWriter(new File(fileName),true));
        }catch(IOException e){
            throw new RuntimeException("Failed to open log File",e);
        }
    }
    @Override
    public synchronized void append(LogMessage logMessage) {
        try{
            this.writer.append(logFormatter.format(logMessage));
            this.writer.newLine();
            this.writer.flush();
        }catch(IOException e){
            throw new RuntimeException("Failed to write log File",e);
        }finally{
            close();
        }
    }
    public synchronized void close(){
        try{
            this.writer.close();
        }catch(Exception e){
            throw new RuntimeException("Failed to close log File",e);
        }
    }
}
