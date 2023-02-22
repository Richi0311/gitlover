package com.example.employeeservice.Model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDAO {

    static List<Employee> list = new ArrayList<>();

    static {
            list.add(new Employee("Arif",7861,"dharadlar.arif@gmail.com"));
            list.add(new Employee("Farheen",7862,"Farheen@gmail.com"));
            list.add(new Employee("Arfat",7863,"Arfat@gmail.com"));
            list.add(new Employee("Aasif",7864,"Aasif@gmail.com"));
    }

    public static List<Employee> getAllEmployee() {
        return list;
    }

    public static Employee getEmployeeById(int empId) {
        return list.stream().filter(emp ->emp.getEmployeeId()==empId).findAny().orElse(null);

    }

    public Employee saveEmployee(Employee emp){
        emp.setEmployeeId(list.size()+1);
        list.add(emp);
        return emp;

    }


    public Employee deleteEmployee(int empid) {
                Iterator<Employee>  empList = list.iterator();
                while(empList.hasNext())
                {
                    Employee emp = empList.next();
                    System.out.println(emp.getEmployeeId());
                    System.out.println(empid);
                    if(empid == emp.getEmployeeId())
                    {
                        empList.remove();
                        return emp;
                    }
                }
                return null;
    }
}
