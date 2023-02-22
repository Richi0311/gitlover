package com.example.employeeservice.service;


import com.example.employeeservice.Exception.EmployeeNotFound;
import com.example.employeeservice.Model.Employee;
import com.example.employeeservice.Model.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeDAO empDao;

    @GetMapping("/employees")
    public List<Employee> getAll(){
       return  empDao.getAllEmployee();

    }

    @GetMapping(path = "/employee/{EmpId}")
    public EntityModel getEmployeeById(@PathVariable int EmpId){
        Employee service =  empDao.getEmployeeById(EmpId);
        if(null== service)
            throw new EmployeeNotFound("Employee Not Found");
        EntityModel<Employee> model = EntityModel.of(service);
        Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        model.add(link);
        return model;
    }

    @PostMapping("/employee/user")
    public ResponseEntity<Object> saveEmployee( @Valid @RequestBody Employee emp){
         Employee service = empDao.saveEmployee(emp);
         URI uir = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{emp}")
                        .buildAndExpand(service.getEmployeeId())
                .toUri();
         return ResponseEntity.created(uir).build();
    }

    @DeleteMapping(path = "/employee/delete/{EmpId}")
    public void deleteEmployee(@PathVariable int EmpId){
           Employee emp = empDao.deleteEmployee(EmpId);
           if(emp == null)
               throw new EmployeeNotFound("Employee not found");
    }

}
