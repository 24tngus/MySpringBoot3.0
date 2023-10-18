package com.basic.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // forward 쓰면 요청 정보가 그대로 유지
    // redirect 요청 정보를 새로 전송
    @GetMapping("/")
    public String index() {
        return "redirect:/userspage/index";
//        return "forward:/userspage/index";
//        return "redirect:/userspage/first"; // leaf.html 요청되어 출력
    }
}
