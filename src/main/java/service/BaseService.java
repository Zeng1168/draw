package service;

import org.apache.ibatis.session.SqlSession;
import utils.SqlSessionFactoryUtil;
import java.lang.reflect.ParameterizedType;

/**
 * 获取数据库会话和Dao层示实例
 * @param <T>
 *          Dao层类名
 */
public abstract class BaseService <T> {
    protected SqlSession sqlSession;    // 数据库会话
    protected T dao;    // dao层实例

    // 获取数据库会话
    protected boolean openSqlSession(){
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            // 反射机制获取泛型类
            Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            dao = sqlSession.getMapper(tClass);

        } catch (Exception e) {
            System.out.println(e);
            sqlSession.rollback();
        }
        return sqlSession != null && dao != null;
    }

    // 结束数据库会话
    protected void closeSqlsession(){
        if(sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
        }
    }

}
