package br.ufpb.dcx.esa.medievalbank.utils.logging;

import java.util.*;

public class Logging implements Logger {

    Map<Date, String> logs;

    public Logging() {
        this.logs = new HashMap<>();
    }

    @Override
    public void trace(String log) {
        this.logs.put(new Date(), String.format("DEBUG: %s", log));
    }

    @Override
    public void info(String log) {

    }

    @Override
    public void warn(String log) {

    }

    @Override
    public void error(String log) {

    }

    @Override
    public void success(String log) {
        this.logs.put(new Date(), String.format("SUCCESS: %s", log));
    }

    @Override
    public List<String> getLogs() {
        List<String> newLogArray = new ArrayList<>();
        for (Map.Entry<Date, String> dateStringEntry : this.logs.entrySet()) {
            Map.Entry pair = (Map.Entry) dateStringEntry;
            newLogArray.add(String.format("%s %s", pair.getKey().toString(), pair.getValue()));
        }
        return newLogArray;
    }
}
