package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Employee;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/11/1
 * Time: 16:21
 * Description:EmployeeMapperPlus映射接口
 */
public interface EmployeeMapperPlus {
    Employee getEmpById(Integer id);

    Employee getEmpAndDeptById(Integer id);

    Employee getEmpByIdStep(Integer id);
}
