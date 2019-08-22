package com.clinicware.service.api;

import com.clinicware.data.RegisterValidation;
import com.clinicware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/register", produces = "text/xml")
@CrossOrigin("*")
public class RegisterRestController {

    private UserService userService;

    @GetMapping
    public String toNew(){
        return "/regist";
    }

    @Autowired
    public RegisterRestController(UserService userService) {
        this.userService = userService;
        System.out.println("constructed");
    }

    @RequestMapping(path = "/regist/{user}", produces = "text/xml")
    @ResponseBody
    public RegisterValidation addNewUser(@PathVariable String user){
//        System.out.println(user.getUsername() + " " + user.getPassword());
        System.out.println(user);
        return null;
        //userService.registerNewUser(user);
    }
}
