package br.ufpb.dcx.esa.medievalbank.utils.logging;

import java.util.*;

public class LoggingMock implements Logger {

    List<String> logs;

    public LoggingMock() {
        this.logs = new ArrayList<>();
    }

    @Override
    public void trace(String log) {
        this.logs.add(String.format("TRACE: %s", log));
    }

    @Override
    public void info(String log) {
        this.logs.add(String.format("INFO: %s", log));
    }

    @Override
    public void warn(String log) {
        this.logs.add(String.format("WARN: %s", log));
    }

    @Override
    public void error(String log) {
        this.logs.add(String.format("ERROR: %s", log));
    }

    @Override
    public void success(String log) {
        this.logs.add(String.format("SUCCESS: %s", log));
    }

    @Override
    public List<String> getLogs() {
        return this.logs;
    }
}
