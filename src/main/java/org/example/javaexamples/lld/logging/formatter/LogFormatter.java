package org.example.javaexamples.lld.logging.formatter;

import org.example.javaexamples.lld.logging.models.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
