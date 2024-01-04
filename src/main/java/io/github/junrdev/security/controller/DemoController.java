package io.github.junrdev.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/welcome/{username}")
    public String sayHi(@PathVariable(name = "username") String name){
        return "Hello "+name;
    }

    @GetMapping("/products")
    public String getProducts(){
        return "Products authenticated";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")// limit access to users with admin authorities
    // add EnableMethodSecurity in Security config
    public String checkAdmin(){
        return "Admin authenticated";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")// limit access to users with admin authorities
    // add EnableMethodSecurity in Security config
    public String checkUser(){
        return "User authenticated";
    }

}
