package com.userRegistrationService.userregistration.controller;

import com.userRegistrationService.userregistration.Exception.UserAlreadyExistsException;
import com.userRegistrationService.userregistration.domain.User;
import com.userRegistrationService.userregistration.service.UserImpl;
import com.userRegistrationService.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

   private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        System.out.println("got request");
        try{
            userService.saveUser(user);
            responseEntity= new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping(path = "/userid/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        System.out.println("getfound");
        try{
            User user= userService.getUserById(id);
            responseEntity= new ResponseEntity<String>(user.toString(),HttpStatus.FOUND);
        }catch (Exception e){
            System.out.println("notfound");
            responseEntity= new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user ,@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try {
            userService.updateUserById(id,user);
            responseEntity = new ResponseEntity<String>("Successfully Updated",HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity =new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
