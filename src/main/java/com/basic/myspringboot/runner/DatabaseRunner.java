package com.basic.myspringboot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
@Order(1)
@Slf4j // lombok에서 제공하고, 로깅퍼사드 기능 (로거 객체 만들지 않고 log로 사용 가능)
public class DatabaseRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("DataSource 구현 클래스명 {}",dataSource.getClass().getName());
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("DB Product Name = {}", metaData.getDatabaseProductName());
            log.info("DB URL = {}",metaData.getURL());
            log.info("DB Username = {}",metaData.getUserName());

        }
    }
}