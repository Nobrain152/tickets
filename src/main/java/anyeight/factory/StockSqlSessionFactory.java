package anyeight.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by T5-SK on 2017/5/14.
 */
public class StockSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory = null;
    //初始化创建SqlSessionFactory对象
    static{
        //读取mybatis-config-test.xml文件
//        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-test.xml");
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config-test.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
    }

    //获取SqlSession对象的静态方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
