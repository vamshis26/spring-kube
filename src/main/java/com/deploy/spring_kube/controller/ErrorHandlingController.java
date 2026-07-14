package com.deploy.spring_kube.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
public class ErrorHandlingController {

    @GetMapping("/ok")
    public ResponseEntity<String> getOkMessage() {
        return ResponseEntity.ok(
                "This is " + HttpStatus.OK
        );
    }

    @GetMapping("/bad-request")
    public ResponseEntity<String> getBadRequest(){
        return ResponseEntity.badRequest().body("This is " + HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/isa")
    public ResponseEntity<String> getISA(){
        return ResponseEntity.internalServerError().body(
                "This is " + HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}