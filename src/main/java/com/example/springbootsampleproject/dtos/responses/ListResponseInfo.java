package com.example.springbootsampleproject.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ListResponseInfo<T> {
    private List<T> data;
    private Integer code;
    private String msg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;

    public ListResponseInfo() {
        this.timestamp = LocalDateTime.now();
    }
}
