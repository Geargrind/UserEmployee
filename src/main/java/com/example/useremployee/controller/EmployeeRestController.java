package com.example.useremployee.controller;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee){
        //sikr at det er INSERT
        employee.setId(0);
        //hent bruger på userID til svar
        Optional<User> userOptional = userRepository.findById(employee.getUser().getUserID());
        if (userOptional.isPresent()) {
            employee.setUser(userOptional.get());
            Employee returEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(returEmployee, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
