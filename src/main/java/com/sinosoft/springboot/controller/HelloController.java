package com.sinosoft.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${person.last-name}")
    private String name;

    @RequestMapping("/sayHi")
    public String sayHi() {
        return "Hello" + name;
    }
}