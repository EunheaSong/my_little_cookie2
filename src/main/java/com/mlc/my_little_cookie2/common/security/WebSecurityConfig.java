package com.mlc.my_little_cookie2.common.security;

import com.mlc.my_little_cookie2.common.filter.JwtAuthFilter;
import com.mlc.my_little_cookie2.common.filter.LoginFilter;
import com.mlc.my_little_cookie2.common.jwt.HeaderTokenExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class WebSecurityConfig {

    private final HeaderTokenExtractor headerTokenExtractor;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement((sessionManagement) ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests((authorizeRequests) ->
                    authorizeRequests.anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public LoginFilter loginFilter() {
        LoginFilter loginFilter = new LoginFilter(authenticationManager());
        loginFilter.setFilterProcessesUrl("/user/login");
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        loginFilter.afterPropertiesSet();
        return loginFilter;
    }
    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

//    @Bean
//    public JwtAuthFilter jwtFilter() {
//        List<String> skipPathList = new ArrayList<>();
//
//        FilterSkipMatcher matcher = new FilterSkipMatcher(
//                skipPathList,
//                "/**"
//        );
//
//        JwtAuthFilter filter = new JwtAuthFilter(
//                matcher,
//                headerTokenExtractor
//        );
//        filter.setAuthenticationManager(super.authenticationManagerBean());
//
//        return filter;
//    }

    public AuthenticationManager authenticationManager() {
        return authentication -> null;
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // Static 정보 접근 허용
        skipPathList.add("GET,/images/**");
        skipPathList.add("GET,/css/**");

        // h2-console 허용
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");

        // 회원 관리 API 허용
        skipPathList.add("GET,/user/**");
        skipPathList.add("POST,/user/join"); //singUp 에서 join 으로 수정!
        skipPathList.add("POST,/user/userName/check"); // 회원가입 중복 체크
        skipPathList.add("POST,/user/email/check"); // 회원가입 중복 체크

        skipPathList.add("GET,/");
        skipPathList.add("GET,/search");  //검색허용
        skipPathList.add("GET,/user/kakao/callback"); //카카오 소셜 로그인 허용

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

}
