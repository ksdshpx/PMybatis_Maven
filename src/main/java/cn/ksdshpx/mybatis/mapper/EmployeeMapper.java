package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/3
 * Time: 13:31
 * Description:Employee映射接口
 * MyBatis允许增删改直接定义一下类型的返回值
 * int/Integer、long/Long、boolean/Boolean、void
 */
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByMap(Map map);

    List<Employee> getEmpsByLastNameLike(String lastName);

    //返回一条记录的Map,key为列名,value为对应的值
    Map<String, Object> getEmpByIdReturnMap(Integer id);

    //多条记录封装成Map,key为这条记录的主键，value为对应的JavaBean
    //告诉MyBatis封装Map的时候使用哪个属性作为主键
    @MapKey("id")
    Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);
}
