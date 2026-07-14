package com.deploy.spring_kube.controller;

import com.deploy.spring_kube.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/user/{name}")
    public String getMessage(@PathVariable String name){
        return "Hello "+ name.toUpperCase() + ", We successfully deployed to K8s, now you are seeing version 3.0";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id) {

        if(id <= 0) {
            throw new UserNotFoundException(
                    "User not found with id: " + id
            );
        }

        return "User found with id: " + id;
    }
}
