package com.java.userproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUserEmail(@PathVariable("id") Long id) {
        return id + "@rest.local";
    }
}
