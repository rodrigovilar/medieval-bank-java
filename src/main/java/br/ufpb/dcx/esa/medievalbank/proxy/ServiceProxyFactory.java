package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {
    public static <M> Service<M> newInstance(Service<M> obj, Logger logger) {
        return (Service<M>) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[] {Service.class},
                new ServiceInvocationHandler(obj, logger));
    }
}
