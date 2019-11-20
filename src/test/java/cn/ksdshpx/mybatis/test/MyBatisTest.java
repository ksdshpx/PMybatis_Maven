package cn.ksdshpx.mybatis.test;

import cn.ksdshpx.mybatis.beans.Department;
import cn.ksdshpx.mybatis.beans.Employee;
import cn.ksdshpx.mybatis.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 有数据源等运行环境信息
     * 2.sql映射文件，配置了每一个sql的映射信息以及sql的封装规则等
     * 3.将sql映射文件注册到全局配置文件中
     * 4.写代码
     * 1）根据全局配置文件得到SqlSessionFactory
     * 2）使用SqlSessionFactory获取SqlSeesion对象，使用它来执行sql语句，
     * 一个SqlSession代表和数据库的一次会话，用完需要关闭,SqlSession对象和Connection一样都是非线程安全的,
     * 每次使用都应该获取新的对象
     * 3）使用sql的唯一标识告诉MyBatis执行哪个sql,sql都是保存在sql映射文件中
     *
     * @throws IOException
     */
    @Test
    public void testHelloWorld() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("cn.ksdshpx.mybatis.mapper.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInterfaceProgram() throws IOException {
        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.通过SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取接口的实例，MyBatis会自动创建接口的代理对象
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(mapper.getClass().getName());
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testMapper() throws IOException {
        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.通过SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取接口的实例，MyBatis会自动创建接口的代理对象
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            System.out.println(mapper.getClass().getName());
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCRUD() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            //测试添加数据
            Employee employee = new Employee(null, "tom", "0", "tom@atguigu.com");
            mapper.addEmp(employee);
            System.out.println(employee.getId());

            //测试修改数据
            //Employee employee = new Employee(1,"hotcat","0","hotcat@atguigu.com");
            //mapper.updateEmp(employee);

            //测试删除数据
            //mapper.deleteEmpById(2);

            //手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testMultiParam() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "hotcat");
            //Map<String,Object> map = new HashMap<>();
            //map.put("id",1);
            //map.put("lastName","hotcat");
            //Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelect() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByLastNameLike("%a%");
            for (Employee emp : emps) {
                System.out.println(emp);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            Map<Integer, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%a%");
            System.out.println(map);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectResultMap() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            //Employee emp = mapper.getEmpById(1);
            //Employee emp = mapper.getEmpAndDeptById(1);
            Employee emp = mapper.getEmpByIdStep(1);
            //System.out.println(emp);
            //System.out.println(emp.getDept());
            System.out.println(emp.getDept());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testResultMapCollectionStep() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptByIdStep(1);
            System.out.println(dept.getDeptName());
            //System.out.println(dept.getEmps());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testResultMapDis() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSql() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            //测试if/where
            Employee employee = new Employee(1,"hotcat","1","hotcat@atguigu.com");
            //Employee employee = new Employee(null,"%t%",null,null);
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : emps) {
                System.out.println(emp);
            }
            System.out.println("==================");
            //测试trim
            List<Employee> empsTrim = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : empsTrim) {
                System.out.println(emp);
            }
            System.out.println("==================");
            //测试choose
            List<Employee> empsChoose = mapper.getEmpsByConditionChoose(employee);
            for (Employee emp : empsChoose) {
                System.out.println(emp);
            }
        } finally {
            sqlSession.close();
        }
    }

}
