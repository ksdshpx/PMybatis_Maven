package cn.ksdshpx.mybatis.beans;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/9/2
 * Time: 14:16
 * Description:Employee实体类
 */
public class Employee {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private Department dept;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Employee(String lastName, String gender, String email, Department dept) {
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                '}';
    }
}
