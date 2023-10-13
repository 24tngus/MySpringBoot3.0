package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("/users")
public class UserBasicRestController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        // 하단과 동일 코드
//        if(optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return user;
//        }

        // orElseThrow(Supplier) Supplier의 추상메서드가 T get()
        User user = optionalUser.orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return user;
    }

    // 그냥 (/{email}) 할 경우 숫자인지 문자열인지 인식 못함
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email)
                            .orElseThrow(() -> new BusinessException("요청하신 email에 해당하는 User가 없습니다", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
        // user를 삭제해도 가능
        //return ResponseEntity.ok(user);
        return ResponseEntity.ok(id + " User가 삭제 되었습니다");
    }


}
