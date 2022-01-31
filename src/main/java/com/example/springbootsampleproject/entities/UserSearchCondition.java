package com.example.springbootsampleproject.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class UserSearchCondition {
    private Integer seq;
    private String userKey;
    private String userName;
    private Long createStartAt;
    private Long createEndAt;
}
