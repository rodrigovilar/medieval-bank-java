package br.ufpb.dcx.esa.medievalbank.proxy;

public class Test {
    public static void main(String[] args) {
        Service<String> service = ServiceProxyFactory.newInstance(new MockService<String>());
        service.insert("TesteInsert");
        service.delete("TesteDelete");
    }
}
