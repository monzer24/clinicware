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

    @RequestMapping(path = "/regist", produces = "text/xml")
    public RegisterValidation addNewUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("first") String first,
            @RequestParam("last") String last,
            @RequestParam("title") String title,
            @RequestParam("userType") int userType,
            int entryUser, String device){
        System.out.println("hi");
        return userService.registerNewUser(username, password, first, last, title, userType, entryUser, device);
    }
}
