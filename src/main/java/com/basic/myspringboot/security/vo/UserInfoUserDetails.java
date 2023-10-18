package com.basic.myspringboot.security.vo;

import com.basic.myspringboot.security.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        email=userInfo.getEmail(); // email을 인증할 때 사용 목적
        password=userInfo.getPassword(); // 암호화된 password
        // 권한
        // userInfo.getRoles() : table에 저장된 role 정보
        // split(",") : , 기준으로 파싱하여 ROLE_ADMIN, ROLE_USER 가져옴
        authorities= Arrays.stream(userInfo.getRoles().split(",")) // Stream<String>
                //  Stream<String> -> Stream<SimpleGrantedAuthority)
                // 람다식 .map(SimpleGrantedAuthority::new) =  .map(roleName -> new SimpleGrantedAuthority(roleName))
                .map(SimpleGrantedAuthority::new) // Stream<SimpleGrantedAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // providemanger에서 인증 할 때 하단 메서드 호출
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    } // email을 이용하여 인증 처리

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}