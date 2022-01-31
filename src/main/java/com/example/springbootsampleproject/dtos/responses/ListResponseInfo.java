package com.example.springbootsampleproject.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ListResponseInfo<T> {
    private List<T> data;
    private Integer code;
    private String msg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestampKST;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    public ListResponseInfo() {
        this.timestampKST = LocalDateTime.now();
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
