package cn.ksdshpx.mybatis.service;

import cn.ksdshpx.mybatis.beans.Student;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/4
 * Time: 14:05
 * Description:StudentService接口
 */
public interface StudentService {
    int addStudent(Student student);
    List<Student> queryStudents();
}
