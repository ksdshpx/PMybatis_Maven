package cn.ksdshpx.mybatis.service;

import cn.ksdshpx.mybatis.beans.Student;
import cn.ksdshpx.mybatis.mapper.StudentMapper;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/4
 * Time: 14:06
 * Description:StudentService实现类
 */
public class StudentServiceImpl implements StudentService{
    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int addStudent(Student student) {
        int rows = studentMapper.insertStudent(student);
        return rows;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentMapper.selectStudents();
        return students;
    }
}
