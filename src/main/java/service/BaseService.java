package service;

import java.lang.reflect.ParameterizedType;

/**
 * 获取数据库会话和Dao层示实例
 * @param <T>
 *          Dao层类名
 */
public abstract class BaseService <T> {
    protected T api;    // api层实例

    protected boolean getApiInstance(){
        if(api == null){
            ParameterizedType tClass = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> type = (Class<T>) tClass.getActualTypeArguments()[0];
            try{
                api = type.newInstance();
            }catch (Exception e){
                // Oops, no default constructor
                throw new RuntimeException(e);
            }
        }
        return api != null;
    }

}
