package com.example.useremployee.controller;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

/*    @Autowired
    UserRepository userRepository;*/

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee){
        employee.setId(0);
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    /*@PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        user.setUserID(0);
        System.out.println(user);
        return userRepository.save(user);
    }*/
}
