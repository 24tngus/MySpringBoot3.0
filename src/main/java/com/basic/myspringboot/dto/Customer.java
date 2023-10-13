package com.basic.myspringboot.dto;

import lombok.*;

@NoArgsConstructor  // 기본 생성자 생성
@AllArgsConstructor // 오버로딩된 생성자 생성
@Getter   // getter 생성
@Setter   // setter 생성
@ToString // toString 생성
@Builder  // Builder pattern 적용한 builder inner class로 값을 전달하고 싶은 데이터만 선택 가능
public class Customer {
    private String name;
    private int age;
}