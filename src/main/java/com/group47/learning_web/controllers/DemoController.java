package com.group47.learning_web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("Hello from security endpoint");
    }

}
