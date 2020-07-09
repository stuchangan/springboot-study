package com.javaboy.securitydb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChangAn
 * @date 2020/7/8 15:00
 */
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello security db";
    }
    @GetMapping("/dba/hello")
    public String dba(){
        return "hello dba";
    }
    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin";
    }
    @GetMapping("/user/hello")
    public String user(){
        return "hello user";
    }

}
