package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.annotation.LOG;

@LOG
public class MockService<M> implements Service<M> {

    @Override
    public M insert(M model) {
        System.out.println("Inserindo " + model);
        return null;
    }

    @Override
    public void delete(M model) {
        System.out.println("Deletando " + model);
    }
}
