package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Student;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/4
 * Time: 13:49
 * Description:StudentMapper类
 */
public interface StudentMapper {
    int insertStudent(Student student);
    List<Student> selectStudents();
}
