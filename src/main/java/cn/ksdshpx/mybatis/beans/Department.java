package cn.ksdshpx.mybatis.beans;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/11/4
 * Time: 9:30
 * Description:部门实体
 */
public class Department {
    private Integer id;
    private String deptName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
