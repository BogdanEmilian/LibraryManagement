package org.example.librarymanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
