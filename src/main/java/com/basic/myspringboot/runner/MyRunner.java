package com.basic.myspringboot.runner;

import com.basic.myspringboot.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Value("${myboot.fullName}")
    private String fullName;

    @Autowired
    private Environment environment;

    @Autowired
    private Customer customer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Customer 현재 모드 = " + customer.getName());
        // 포트 번호 받아오기
        System.out.println("Port Number = " + environment.getProperty("local.server.port"));

        // 환경변수 받아오기
        System.out.println("myboot.name = " + name);
        System.out.println("myboot.age = " + age);
        System.out.println("myboot.fullName = " + fullName);

        // ApplicationArguments는 main메서드의 (String[] args) argument를 전달 받음
        System.out.println("VM Argument foo = " + args.containsOption("foo"));
        System.out.println("Program argument bar = " + args.containsOption("bar"));

    }
}
