package com.example.employeeservice.service;

import com.example.employeeservice.Exception.DepartmentNotFound;
import com.example.employeeservice.Exception.EmployeeNotFound;
import com.example.employeeservice.Model.Department;
import com.example.employeeservice.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmloyeeRepoController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    DepartmentRepository depRepository;

    @GetMapping("/jpa/employees")
    public List<Employee> getAll(){
        return  repository.findAll();

    }

    @GetMapping(path = "/jpa/employee/{EmpId}")
    public EntityModel getEmployeeById(@PathVariable long EmpId){
        Employee service =  repository.findById(EmpId).get();
        if(null== service)
            throw new EmployeeNotFound("Employee Not Found");
        EntityModel<Employee> model = EntityModel.of(service);
        Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        model.add(link);
        return model;
    }

    @PostMapping("/jpa/employee/emp")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp){
        Employee service = repository.save(emp);
        URI uir = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{emp}")
                .buildAndExpand(service.getEmployeeId())
                .toUri();
        return ResponseEntity.created(uir).build();
    }

    @PostMapping("/jpa/department/depart")
    public ResponseEntity<Object> saveDepartment(@Valid @RequestBody Department dep){
        Department service = depRepository.save(dep);
        URI uir = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{emp}")
                .buildAndExpand(service.getDepartment())
                .toUri();
        return ResponseEntity.created(uir).build();
    }

    @GetMapping(path = "/jpa/department/{id}")
    public EntityModel getDepartmentById(@PathVariable long id){
        Department service =  depRepository.findById(id).get();
        if(null== service)
            throw new DepartmentNotFound("Department Not Found");
        EntityModel<Department> model = EntityModel.of(service);
        Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll()).withRel("all-employees");
        model.add(link);
        return model;
    }
    @DeleteMapping(path = "jpa/employee/delete/{EmpId}")
    public void deleteEmployee(@PathVariable long EmpId){
        Employee service =  repository.findById(EmpId).get();
        if(null== service)
            throw new EmployeeNotFound("Employee Not Found");
        repository.delete(service);
    }
}
