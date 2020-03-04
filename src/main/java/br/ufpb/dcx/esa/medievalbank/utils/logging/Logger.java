package br.ufpb.dcx.esa.medievalbank.utils.logging;

import java.util.List;

public interface Logger {
    public void trace(String log);
    public void info(String log);
    public void warn(String log);
    public void error(String log);
    public void success(String log);

    public List<String> getLogs();
}
