package br.ufpb.dcx.esa.medievalbank.proxy;

public interface Service<M> {
    public M insert(M model);
    public void delete(M model);
}
