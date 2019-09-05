package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/3
 * Time: 13:31
 * Description:Employee映射接口
 */
public interface EmployeeMapper {
    Employee getEmpById(Integer id);
}
