package controller;

import java.lang.reflect.ParameterizedType;

public class BaseController <T> {
    protected T service;

    protected boolean getServiceInstance(){
        ParameterizedType tClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> type = (Class<T>) tClass.getActualTypeArguments()[0];
        try{
            service = type.newInstance();
        }catch (Exception e){
            // Oops, no default constructor
            throw new RuntimeException(e);
        }

        return service != null;
    }
}
