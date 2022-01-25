package com.werun.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author werun
 * @version 1.0
 * @date 2022年01月24日 下午6:55
 * @description
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();

        return "Hello, " + a.getName() + "!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println(username);

        // do something with the username
    }
}
