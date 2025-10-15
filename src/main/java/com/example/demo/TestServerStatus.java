package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestServerStatus {
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
