package com.clinicware.service.api;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.User;
import com.clinicware.data.UserRepository;
import com.clinicware.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

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
//
//    @GetMapping
//    public String register(){
//        return "/register";
//    }

    @Autowired
    public RegisterRestController(UserService userService, UserRepository repo) {
        this.userService = userService;
        this.repo = repo;
        System.out.println("constructed");
    }

    @RequestMapping(path = "/{user}", headers = ("accept=*/*"), produces = "application/xml")
    public @ResponseBody
    ResponseEntity<RegisterValidation> addNewUser(@RequestBody User user) throws Exception{
//        System.out.println(user.getUsername() + " " + user.getPassword());
//        User user = new User(username, pass word, first, last, title, userType);
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("User is : " + user);

//        User reg = mapper.convertValue(user, User.class);
        return userService.registerNewUser(user);
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
