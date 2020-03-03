package br.ufpb.dcx.esa.medievalbank.proxy;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {
    public static <M> Service<M> newInstance(Service<M> obj) {
        return (Service<M>) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class<?>[] {Service.class},
                new ServiceInvocationHandler(obj));
    }
}
