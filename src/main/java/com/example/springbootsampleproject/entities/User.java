package com.example.springbootsampleproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity(name="User")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql 같은 경우 AutoIncrement 적용
    private Integer seq; // pk 값

    @Column(nullable = false, unique = true, length = 20)
    private String userKey; // 회원 식별키

    @Column(nullable = false, length = 50)
    private String userName; // 회원 이름

    @CreationTimestamp
    @Column(columnDefinition="TIMESTAMP")
    private Timestamp createdAt; // 생성일

}
