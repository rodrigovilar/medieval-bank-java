package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.annotation.LogMethod;
import br.ufpb.dcx.esa.medievalbank.utils.logging.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {
    private Object obj;
    private Logger logger;

    public ServiceInvocationHandler(Object obj, Logger logger) {
        this.obj = obj;
        this.logger = logger;
    }

    Object handleLogInvocation(Method method, Object[] args) throws Throwable {
        this.logger.trace(String.format("Calling method %s", method.getName()));
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (Throwable e) {
            this.logger.error(String.format("Exception caught in method %s. Exception message was: %s", method.getName(), e.getCause().getMessage()));
            throw e;
        }
        logger.success(String.format("Succesfully finished call to method %s", method.getName()));
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            Method objMethod = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (objMethod.isAnnotationPresent(LogMethod.class)) {
                result = handleLogInvocation(method, args);
            } else {
                result = method.invoke(obj, args);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
