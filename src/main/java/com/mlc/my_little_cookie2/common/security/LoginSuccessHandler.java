package com.mlc.my_little_cookie2.common.security;

import com.mlc.my_little_cookie2.common.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";

    public static final String TOKEN_TYPE = "BEARER";


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());

        final String token = JwtProvider.issuedToken(userDetails);

        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);


    }

}
