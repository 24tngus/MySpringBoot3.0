package com.basic.myspringboot.runner;

import com.basic.myspringboot.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2)
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

    //로거 생성
    Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // info
        logger.info("Logger 클래스 이름 {}", logger.getClass().getName()); // ch.qos.logback.classic.Logger
        logger.info("Customer 현재 모드 = {}", customer.getName());
        logger.info("Port Number = {}", environment.getProperty("local.server.port"));
        // 환경변수 받아오기
        logger.info("myboot.name = {}", name);
        logger.info("myboot.age = {}", age);
        logger.info("myboot.fullName = {}", fullName);


        // debug
        // ApplicationArguments는 main메서드의 (String[] args) argument를 전달 받음
        logger.debug("VM Argument foo = {} Program argument bar = {}",
                args.containsOption("foo")
                , args.containsOption("bar")
        );

    }
}
