package cn.ksdshpx.mybatis.test;

import cn.ksdshpx.mybatis.beans.Department;
import cn.ksdshpx.mybatis.beans.Employee;
import cn.ksdshpx.mybatis.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
     * MyBatis运行流程
     * 1.获取SqlSessionFactory对象
     *      解析配置文件的每一个信息保存在Configuration中，返回包含Configuration
     *      的DefaultSqlSessionFactory
     *      注意：MappedStatement:代表增删改查的详细信息
     *
     * 2.获取SqlSession对象
     *      返回一个DefaultSqlSession,包含Executor和Configuration
     *      这一步会创建Executor对象
     *
     * 3.获取接口的代理对象
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
            //Employee employee = new Employee(1,"hotcat","1","hotcat@atguigu.com");
            Employee employee = new Employee(1, "sfituser", null, null);
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
            //测试set
            System.out.println("=============");
            mapper.updateEmp(employee);
            System.out.println("=============");
            List<Employee> empsForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
            for (Employee emp : empsForeach) {
                System.out.println(emp);
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testBatchSave() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取的sqlSession不自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee("kraka","1","smith@163.com",new Department(1)));
            emps.add(new Employee("fider","0","allen@163.com",new Department(1)));
            mapper.addEmps(emps);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInnerParameter() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = mapper.getEmpsByInnerParamter(new Employee("t"));
            emps.forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }

    /*
        缓存：
        1.一级缓存:(本地缓存),SqlSession级别的缓存,一级缓存是一直开启的,其实就是SqlSession级别的一个Map
            与数据库同一次会话期间查询到的数据会放在本地缓存中
            以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库

            一级缓存失效情况（没有使用到一级缓存的情况，还需要向数据库发送查询）
            ①SqlSession不同
            ②SqlSession相同，查询条件不同（当前一级缓存中还没有这个数据）
            ③SqlSession相同，两次查询之间执行了增删改操作（这次增删改可能对当前的数据有影响）
            ④SqlSession相同，手动清除了一级缓存（缓存清空）
        2.二级缓存:(全局缓存),namespace级别的缓存，一个namespace对应一个二级缓存
            工作机制:
            ①一个会话，查询一条数据，这个数据会放在当前会话的一级缓存中
            ②如果会话关闭了，一级缓存中的数据会被保存到二级缓存中，新的会话查询信息就可以参照
              二级缓存中的内容
            ③SqlSession===EmployeeMapper==>Employee
                           DepartmentMapper===>Department
              不同namespace查出的数据是放在自己对应的缓存(Map)中

            效果:数据会从二级缓存中获取
                查出的数据默认都先放在一级缓存中
                只有会话提交或者关闭以后一级缓存中的数据才会转移到二级缓存中

            使用:
                1)开启全局二级缓存配置
                    <setting name="cacheEnabled" value="true"/>
                2)去mapper.xml中配置使用二级缓存
                    <cache></cache>
                3)我们的POJO需要实现序列化接口


            和缓存有关的设置/属性
                1)cacheEnabled=true
                    false表示不使用二级缓存，但是一级缓存仍可用
                2)每个select标签都有useCache="true"
                    false表示不使用二级缓存，但是一级缓存仍可用
                3)每个增删改标签都有flushCache="true",表示增删改执行完后就会清除缓存
                    注意：①flushCache="true"时一级缓存和二级缓存都会被清除
                         ②查询标签:默认flushCache="false",如果改为true,每次查询之后都会清空缓存,即缓存是没有被使用的
                4)sqlSession.clearCache():只清除当前session的一级缓存，和二级缓存没关系
                5)localCacheScope:本地缓存作用域（影响一级缓存）,默认为SESSION,当前会话的所有数据保存在一级缓存中
                     如果设置为STATEMENT,相当于是禁用缓存

            缓存的使用顺序:先看二级缓存，再看一级缓存，最后才会查数据库

        第三方缓存整合
            1)导入第三方缓存包即可
            2)导入与第三方缓存整合的适配包
            3)在mapper.xml使用自定义缓存
                <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
    */
    @Test
    public void testFirstLevelCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmpById(1);
            System.out.println(emp01);
            System.out.println("处理业务逻辑");
            Employee emp02 = mapper.getEmpById(1);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFirstLevelCacheInvalid() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmpById(1);
            System.out.println(emp01);
            System.out.println("处理业务逻辑");
            //1.SqlSession不同
            //SqlSession sqlSession2 = sqlSessionFactory.openSession();
            //mapper = sqlSession2.getMapper(EmployeeMapper.class);
            //2.查询条件不同
            //Employee emp03 = mapper.getEmpById(3);
            //3.两次查询之间进行了增删改操作
            //mapper.addEmp(new Employee("aaa"));
            //4.清除缓存
            sqlSession.clearCache();
            Employee emp02 = mapper.getEmpById(1);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSecondLevelCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmpById(1);
            System.out.println(emp01);
            sqlSession.close();
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            //第二次查询是从缓存中拿到的数据，并没有发送新的sql
            Employee emp02 = mapper2.getEmpById(1);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);
            sqlSession2.close();
        } finally {
            //sqlSession.close();
        }
    }

    @Test
    public void testMBG() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
