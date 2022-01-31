package com.example.springbootsampleproject.libraries;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.springframework.util.ObjectUtils.isEmpty;

public class CommonLibrary {
    public static String getMakeToken(Integer length) {
        String strings = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Date date = new Date();
        long timestamp = date.getTime(); // ex) 1643531492042 => 13자리
        Random random = new Random();
        Integer randomStringLength = length - 13;

        if (length < 13) throw new IllegalArgumentException("length must be a positive number.");
        StringBuilder sb = new StringBuilder(randomStringLength);
        for (int i = 0; i < randomStringLength; i++) {
            sb.append(strings.charAt(random.nextInt(strings.length())));
        }

        sb.insert(CommonLibrary.getRandomNumber(0, sb.toString().length() - 1), timestamp + "");
        return sb.toString();
    }

    public static Integer getRandomNumber(Integer min, Integer max) {
        Random random = new Random();
        Integer value = random.nextInt(max + min) + min;
        return value;
    }

    public static Timestamp getTimestamp(Long time) {
        Date date = new Date(time);
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
