package cn.ksdshpx.mybatis.test;

import cn.ksdshpx.mybatis.beans.Student;
import cn.ksdshpx.mybatis.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/4
 * Time: 14:56
 * Description:测试MyBatisSpring集成
 */
public class MyBatisSpringTest {
    private StudentService studentService;
    @Before
    public void init(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentService = ctx.getBean("studentService", StudentService.class);
    }

    @Test
    public void testAddStudent(){
        Student student = new Student();
        student.setName("李四");
        student.setAge(30);
        studentService.addStudent(student);
    }

    @Test
    public void testQueryStudents(){
        List<Student> students = studentService.queryStudents();
        System.out.println(students);
    }
}
