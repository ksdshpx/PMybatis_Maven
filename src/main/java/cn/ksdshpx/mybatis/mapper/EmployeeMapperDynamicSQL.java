package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/11/18
 * Time: 9:50
 * Description:动态SQL的Mapper接口
 */
public interface EmployeeMapperDynamicSQL {
    //携带了哪个字段，查询条件就带上这个字段的值
    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionTrim(Employee employee);

    List<Employee> getEmpsByConditionChoose(Employee employee);

    void updateEmp(Employee employoe);

    List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    void addEmps(@Param("emps") List<Employee> emps);

    List<Employee> getEmpsByInnerParamter(Employee employee);
}
