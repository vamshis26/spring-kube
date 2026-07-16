package com.deploy.spring_kube.controller;

import com.deploy.spring_kube.exceptions.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/user/{name}")
    @ResponseBody
    public String getMessage(@PathVariable String name) {
        return "Hello " + name.toUpperCase()
                + ", We successfully deployed to K8s, now you are seeing version 3.0";
    }

    @GetMapping("/user/about-me")
    public String aboutMe() {
        return "about-me";
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public String getUser(@PathVariable Long id) {

        if(id <= 0) {
            throw new UserNotFoundException(
                    "User not found with id: " + id
            );
        }

        return "User found with id: " + id;
    }
}