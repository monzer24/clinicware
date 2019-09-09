package com.clinicware.service.api;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.User;
import com.clinicware.data.UserRepository;
import com.clinicware.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin("*")
public class RegisterRestController {

    private UserService userService;
    private UserRepository repo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    public RegisterRestController(UserService userService, UserRepository repo) {
        this.userService = userService;
        this.repo = repo;
        System.out.println("constructed");
    }

    @RequestMapping(path = "/{user}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<RegisterValidation> addNewUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        ResponseEntity<RegisterValidation> response = null;
        if(repo.findUserByUsername(user.getUsername()) == null){
            response = userService.registerNewUser(user);
        }
        System.out.println("User is : " + user);
        return response;
    }

    @RequestMapping(path = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable String id){
//        System.out.println(user.getUsername() + " " + user.getPassword());
        System.out.println("User are : " + id);
        return repo.findById(id).get();
        //userService.registerNewUser(user);
    }
}
