package com.javaboy.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChangAn
 * @date 2020/7/10 16:05
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello jwt";
    }
    @GetMapping("/admin")
    public String admin(){
        return "hello admin jwt";
    }
}
