package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/3
 * Time: 13:31
 * Description:Employee映射接口
 *      MyBatis允许增删改直接定义一下类型的返回值
 *          int/Integer、long/Long、boolean/Boolean、void
 */
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByMap(Map map);

    List<Employee> getEmpsByLastNameLike(String lastName);
}
