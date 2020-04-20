package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 创建SqlSessionFactory的工具类，实现为单例模式
 *
 */
public class SqlSessionFactoryUtil {

    // SqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory = null;
    // 类线程锁
    private static final Class<SqlSessionFactoryUtil> CLASS_LOCK = SqlSessionFactoryUtil.class;

    /**
     * 私有化默认构造函数
     */
    private SqlSessionFactoryUtil() {

    }

    /**
     * 构建SqlSessionFactory
     * @return
     */
    public static SqlSessionFactory initSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (Exception e) {
            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, e);
        }

        // 为了避免并发状态下的SqlSessionFactory不唯一，需要添加锁
        synchronized(CLASS_LOCK){
            if(sqlSessionFactory == null){
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 获得一个新的Session
     * @return
     */
    public static SqlSession openSession(){
        if(sqlSessionFactory == null){
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }

}