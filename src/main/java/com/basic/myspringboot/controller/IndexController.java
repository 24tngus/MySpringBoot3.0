package com.basic.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:/userspage/index";
//        return "redirect:/userspage/first"; // leaf.html 요청되어 출력
    }
}