package com.example.springbootsampleproject.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
public class LoginResponseInfo {
    private String msg;
    private Integer code;
    private String accessToken;
    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestampKST;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    public LoginResponseInfo() {
        this.timestampKST = LocalDateTime.now();
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
