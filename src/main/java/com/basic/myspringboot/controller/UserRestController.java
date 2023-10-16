package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // url 똑같으면 겹치니까 다르게 작성
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    // 등록
    @PostMapping
    public UserResDTO saveUser(@RequestBody UserReqDTO userReqDTO) {
        return userService.saveUser(userReqDTO); // service에서 모두 처리되어 controller에서는 호출만 수행
    }

    // 조회
    @GetMapping("/{id}")
    public UserResDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 전체 목록 조회
    @GetMapping
    public List<UserResDTO> getUsers() {
        return userService.getUsers();
    }

    // 수정
    @PatchMapping("/{email}")
    public UserResDTO updateUser(@PathVariable String email, @RequestBody UserReqDTO userReqDTO) {
        return userService.updateUser(email, userReqDTO);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id + " User가 삭제처리 되었습니다.");
    }
}
