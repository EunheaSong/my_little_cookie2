package com.mlc.my_little_cookie2.common.jwt;

import com.mlc.my_little_cookie2.common.security.UserDetailsImpl;
import com.mlc.my_little_cookie2.common.security.UserDetailsServiceImpl;
import com.mlc.my_little_cookie2.common.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    //유효기간 , 담을 정보 , 시크릿 키

    @Value("${my.secret_key}")
    private static String secretKey;

    @Value("${my.expired_sec}")
    private static int expiredSec;

    @Value("${my.expired_min}")
    private static int expiredMin;

    public static String issuedToken(UserDetailsImpl user) {
        String token = null;
        try {
            Claims claims = Jwts.claims();
            claims.put("id", user.getId());
            claims.put("nickname", user.getNickname());
            Date now = new Date();
            token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + expiredSec * expiredMin))
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return token;
    }


}
