package com.example.springbootsampleproject.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class FindAllUserRequestInfo {
    private Integer seq;
    private String userKey;
    private Timestamp createStartAt;
    private Timestamp createEndAt;
    private String userName;
}
