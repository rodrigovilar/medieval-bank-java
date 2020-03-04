package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;
import br.ufpb.dcx.esa.medievalbank.utils.logging.LoggingMock;

public class Test {
    public static void main(String[] args) {
        Logger logger = new LoggingMock();

        Service<String> service = ServiceProxyFactory.newInstance(new MockService<>(), logger);
        service.insert("TesteInsert");
        try {
            service.delete("TesteDelete");
        } catch (Exception e) {

        }
        for(String i: logger.getLogs()){
            System.out.println(i);
        }
    }
}
