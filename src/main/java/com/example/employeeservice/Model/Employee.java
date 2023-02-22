package com.example.employeeservice.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {

    //@Size(min = 3,max = 5,message = "Invalid chars for name")
    private String name;

    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.REMOVE)
    private List<Department> allDepartment;

    public List<Department> getAllDepartment() {
        return allDepartment;
    }

    public void setAllDepartment(List<Department> allDepartment) {
        this.allDepartment = allDepartment;
    }

    public Employee() {

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    @Email
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name, int employeeId, String mail) {
        this.name = name;
        this.employeeId = employeeId;
        this.mail = mail;
    }
}
