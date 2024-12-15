package be.kdg.java2.gameproject.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.LogRecord;

public class SmallLogFormatter extends java.util.logging.Formatter{
    @Override
    public String format(LogRecord record) {
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String message = MessageFormat.format(record.getMessage(), record.getParameters());
        return String.format("%s Level: %s melding: %s\n", ldt,record.getLevel(), message );
    }
}
