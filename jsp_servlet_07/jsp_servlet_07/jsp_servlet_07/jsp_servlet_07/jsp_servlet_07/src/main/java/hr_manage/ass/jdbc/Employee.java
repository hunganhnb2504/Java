package hr_manage.ass.jdbc;

import java.util.Date;

public class Employee {

    private int id;
    private String employee_id;
    private String employee_name;
    private Date birthday;
    private String phone_number;
    private String email;

    public Employee(String employee_id, String employee_name, Date birthday,String phone_number, String email) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Employee(int id, String employee_id, String employee_name, Date birthday, String phone_number, String email) {
        this.id = id;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", employee_id=" + employee_id + ", Name=" + employee_name + "]";
    }
}
