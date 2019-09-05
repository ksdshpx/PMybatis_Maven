package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/5
 * Time: 10:49
 * Description:Employee映射接口(基于注解)
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id=#{id}")
    Employee getEmpById(Integer id);
}
