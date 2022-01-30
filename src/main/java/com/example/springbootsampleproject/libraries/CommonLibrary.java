package com.example.springbootsampleproject.libraries;

import java.util.Date;
import java.util.Random;

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
}
