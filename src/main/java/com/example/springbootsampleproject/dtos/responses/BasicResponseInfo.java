package com.example.springbootsampleproject.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BasicResponseInfo {
    private Integer code;
    private String msg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;

    public BasicResponseInfo() {
        this.timestamp = LocalDateTime.now();
    }
}
