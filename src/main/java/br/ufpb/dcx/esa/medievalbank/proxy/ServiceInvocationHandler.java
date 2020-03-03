package br.ufpb.dcx.esa.medievalbank.proxy;

import br.ufpb.dcx.esa.medievalbank.annotation.LOG;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {
    private Object obj;

    public ServiceInvocationHandler(Object obj) {
        this.obj = obj;
    }

    Object handleInsert(Method method, Object[] args) throws Throwable {
        String modelName = args[0].getClass().getSimpleName();
        System.out.println("Trying to insert " + modelName.toLowerCase());
        Object result = method.invoke(obj, args);
        System.out.println(modelName + " created");
        return result;
    }

    Object handleDelete(Method method, Object[] args) throws Throwable {
        String modelName = args[0].getClass().getSimpleName();
        System.out.println("Trying to delete " + modelName.toLowerCase());
        Object result = method.invoke(obj, args);
        System.out.println(modelName + " deleted");
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            if(obj.getClass().isAnnotationPresent(LOG.class)) {
                if (method.getName().contains("insert")) {
                    result = handleInsert(method, args);
                }else if (method.getName().contains("delete")){
                    result = handleDelete(method, args);
                } else {
                    result = method.invoke(obj, args);
                }
            } else {
                result = method.invoke(obj, args);
            }

        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
