package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.MedievalBankException;
import br.ufpb.dcx.esa.medievalbank.annotation.LOG;
import br.ufpb.dcx.esa.medievalbank.annotation.LogMethod;

@LOG
public class MockService<M> implements Service<M> {

    @Override
    @LogMethod("Insert method")
    public M insert(M model) {
        System.out.println(">> Inserting " + model);
        return null;
    }

    @Override
    @LogMethod("Delete method")
    public void delete(M model) throws RuntimeException {
        throw new MedievalBankException("Method not implemented");
        //System.out.println(">> Deleting " + model);
    }
}
