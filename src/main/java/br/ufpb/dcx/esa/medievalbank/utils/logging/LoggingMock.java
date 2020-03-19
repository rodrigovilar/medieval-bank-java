package br.ufpb.dcx.esa.medievalbank.utils.logging;

import java.util.*;

public class LoggingMock implements Logger {

    List<String> logs;

    public LoggingMock() {
        this.logs = new ArrayList<>();
    }

    public void insertLog(String log) {
        System.out.println(log);
        this.logs.add(log);
    }

    @Override
    public void trace(String log) {
        insertLog(String.format("TRACE: %s", log));
    }

    @Override
    public void info(String log) {
        insertLog(String.format("INFO: %s", log));
    }

    @Override
    public void warn(String log) {
        insertLog(String.format("WARN: %s", log));
    }

    @Override
    public void error(String log) {
        insertLog(String.format("ERROR: %s", log));
    }

    @Override
    public void success(String log) {
        insertLog(String.format("SUCCESS: %s", log));
    }

    @Override
    public List<String> getLogs() {
        return this.logs;
    }
}
