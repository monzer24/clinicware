package com.clinicware.controller;

import com.clinicware.data.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.applet.resources.MsgAppletViewer;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    ObjectMapper mapper;

    @GetMapping
    public String register(){
        return "register";
    }

    @PostMapping("/regist")
    public String regist(User user, RedirectAttributes model, RestTemplate rest) throws JsonProcessingException, JSONException {
        System.out.println(user);
        JSONObject json = new JSONObject(mapper.writeValueAsString(user));
        System.out.println(json);
        Map<String, User> userMap = new HashMap<>();
        userMap.put("user", user);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders header = new HttpHeaders();
        header.add("accept", "*/*");
        HttpEntity<User> entity = new HttpEntity<>(header);
        rest.postForEntity("http://localhost:8080/register/{user}", user, User.class, userMap);
        System.out.println(entity);
        model.addFlashAttribute("user", user);
        return "redirect:/register";
    }

}
