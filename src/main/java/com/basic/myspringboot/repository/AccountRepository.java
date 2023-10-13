package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // < Entity 클래스, PK값 >
    // Insert, Delete, Select만 존재

    // select * from account where username = 'spring'
    Optional<Account> findByUsername(String username);
}
