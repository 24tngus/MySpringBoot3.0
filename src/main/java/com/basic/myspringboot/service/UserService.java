package com.basic.myspringboot.service;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserReqForm;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList; // Collectors.toList()에서 Collectors 생략 가능

@Service
@RequiredArgsConstructor // lombok에 final로 선언된 변수들의 생성자 만들어줌
@Transactional
public class UserService {
    // 해당 방식은 Setter Injection
    // @Autowired
    // private UserRepository userRepository;

    // Constructor Injection 방식
    // @Autowired 사용하지 않고 injection
    // final은 선언과 동시에 초기화 필요 (생성자를 통해 초기화해도 됨)
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // injection 받는 객체 늘어날 경우 개발자가 계속 추가 필요
    // -> lobok의 @RequiredArgsConstructor 사용
//    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//    }

    // 등록
    public UserResDTO saveUser(UserReqDTO userReqDto) {
        //reqDto => entity 매핑
        User user = modelMapper.map(userReqDto, User.class);
        // DB에 저장
        User savedUser = userRepository.save(user);
        //entity => resDto 매핑
        return modelMapper.map(savedUser, UserResDTO.class);
    }

    // 조회
    @Transactional(readOnly = true) // 조회 메서드인 경우에 readOnly=true를 설정하면 성능 향상에 도움
    public UserResDTO getUserById(Long id) {
        User userEntity = userRepository.findById(id) // return type : Optional<User>
                    .orElseThrow(() -> new BusinessException(id + "User Not Found", HttpStatus.NOT_FOUND));
        // Entity -> ResDTO로 변환
        UserResDTO userResDTO = modelMapper.map(userEntity, UserResDTO.class);
        return userResDTO;
    }

    // 전체 목록 조회
    @Transactional(readOnly = true)
    public List<UserResDTO> getUsers() {
        List<User> userList = userRepository.findAll(); // List<User>

        // List<User> -> List<UserResDTO>
        List<UserResDTO> userResDTOList = userList.stream() // List<User> -> Stream<User>
                // map(Function) Function의 추상메서드 : R apply (T t)
                .map(user -> modelMapper.map(user, UserResDTO.class)) // Stream<User> -> Stream<UserResDTO>
                .collect(toList());// Stream<UserResDTO> -> List<UserResDTO>

        return userResDTOList;
    }

    // 수정
    public UserResDTO updateUser(String email, UserReqDTO userReqDto) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        // Dirty Checking 변경 감지를 해서 setter method만 호출해도 update query가 실행됨
        existUser.setName(userReqDto.getName());
        return modelMapper.map(existUser, UserResDTO.class); // User -> UserResDTO
    }

    public void updateUserForm(UserReqForm userReqForm) {
        User existUser = userRepository.findById(userReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(userReqForm.getId() + " User Not Found", HttpStatus.NOT_FOUND));
        existUser.setName(userReqForm.getName());
        // 반환하지 않고 update만 진행
    }

    // 삭제
    public void deleteUser(Long id) {
        User user = userRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
