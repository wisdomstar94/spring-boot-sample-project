package com.example.springbootsampleproject.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="User")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql 같은 경우 AutoIncrement 적용
    private Integer seq; // pk 값

    @Column(nullable = false, unique = true, length = 20)
    private String userKey; // 회원 식별키

    @Column(nullable = false, length = 50)
    private String userName; // 회원 이름

    @Column(nullable = false, length = 50)
    private String userId; // 회원 ID

    @Column(columnDefinition = "TEXT")
    private String userPassword; // 회원 비밀번호

    @CreationTimestamp
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp createdAt; // 생성일

    ///////////////////////////////////////////

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

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
