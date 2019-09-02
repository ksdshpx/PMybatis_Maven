package cn.ksdshpx.mybatis.test;

import cn.ksdshpx.mybatis.beans.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/2
 * Time: 14:41
 * Description:测试文件
 */
public class MyBatisTest {
    /**
     * 1.根据xml配置文件（全局配置文件）创建SqlSessionFactory对象,获取SqlSession实例，能直接执行已经映射的sql语句
     *    有数据源等运行环境信息
     * 2.sql映射文件，配置了每一个sql以及sql的封装规则等
     * 3.将sql映射文件注册到全局配置文件中
     * 4.写代码
     *    1）根据全局配置文件得到SqlSessionFactory
     *    2）使用SqlSessionFactory获取SqlSeesion对象，使用它来执行sql语句，一个SqlSession代表和数据库的一次会话，用完需要关闭
     *    3）使用sql的唯一标识告诉MyBatis执行哪个sql,sql都是保存在sql映射文件中
     * @throws IOException
     */
    @Test
    public void testHelloWorld() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("cn.ksdshpx.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
