package com.example.useremployee.controller;

import com.example.useremployee.model.User;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        user.setUserID(0);
        System.out.println(user);
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> putUser(@PathVariable("id") int id, @RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            user.setUserID(id);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
