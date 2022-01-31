package com.example.springbootsampleproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

@Service
public class JwtService {
    @Resource
    private ObjectMapper objectMapper;

    private static final String SECRET_KEY = "0dPZw1643640246095jPIAaWRGWBhbQ3O7NvtbaiVjXzWiY4APOin";

    /** * jwt 생성 */
    public String createToken(String key, String value, Integer expireTokenDuration) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp expireTimestamp = Timestamp.valueOf(now.plusHours(expireTokenDuration));

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        return Jwts.builder()
                .claim(key, value)
                // .claim(key2, value2)
                // .claim(key3, value3)
                .setExpiration(expireTimestamp)
                .setIssuer("sample")
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
                .setHeaderParam("typ","JWT")
                .compact();
    }

    /** * jwt 검증 * String value = (String) tokenService.getClaims(token).get(key); */
    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            e.printStackTrace();
            // The JWT MUST contain exactly two period characters. 등
            throw e;
        }
    }

    public Map<String, Object> getPayloadWithoutSigning(String token) {
        try {
            String payloadJsonString = new String(Base64.getDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);
            return objectMapper.readValue(payloadJsonString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("잘못된 payload 형식입니다.");
        }
    }
}
