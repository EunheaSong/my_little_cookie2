package com.mlc.my_little_cookie2.common.jwt;

import com.mlc.my_little_cookie2.common.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class JwtProvider {
    //유효기간 , 담을 정보 , 시크릿 키

    @Value("${my.secret_key}")
    private String secretKey;

    @Value("${my.expired_sec}")
    private int expiredSec;

    @Value("${my.expired_min}")
    private int expiredMin;

    public void issuedToken(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.id);
        claims.put("nickname", user.nickname);
        Date now = new Date();
        Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + expiredSec*expiredMin))
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

}
