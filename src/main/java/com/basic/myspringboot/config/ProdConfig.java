package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod") // 현재 어떤 환경인지 properties 파일에 설정 필요
@Configuration
public class ProdConfig {
    @Bean
    public Customer customer() {
        return Customer.builder() // CustomerBuilder inner class
                .name("운영모드")
                .age(50)
                .build(); // customer로 바꿔주는 기능

    }
}
