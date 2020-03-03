package br.ufpb.dcx.esa.medievalbank.proxy;

public class Test {
    public static void main(String[] args) {
        Service<String> service = ServiceProxyFactory.newInstance(new MockService<>());
        service.insert("TesteInsert");
        try {
            service.delete("TesteDelete");
        } catch (Exception e) {

        }
    }
}
