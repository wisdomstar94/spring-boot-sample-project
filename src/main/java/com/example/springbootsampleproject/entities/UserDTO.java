package com.example.springbootsampleproject.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class UserDTO {
    private String outerUserName; // 회원 이름
    private Timestamp outerCreatedAt; // 생성일
}
