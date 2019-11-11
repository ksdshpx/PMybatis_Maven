package cn.ksdshpx.mybatis.mapper;

import cn.ksdshpx.mybatis.beans.Department;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/11/4
 * Time: 11:08
 * Description:Department映射接口
 */
public interface DepartmentMapper {
    Department getDeptById(Integer id);
    Department getDeptByIdPlus(Integer id);
}
