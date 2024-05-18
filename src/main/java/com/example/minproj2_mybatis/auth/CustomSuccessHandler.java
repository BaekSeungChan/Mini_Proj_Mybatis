package com.example.minproj2_mybatis.auth;

import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.auth.entity.CustomOAuth2User;
import com.example.minproj2_mybatis.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username;
        String role;

        Object principal = authentication.getPrincipal();
        if(principal instanceof CustomMemberDetailsService){
            CustomMemberDetailsService memberDetail = (CustomMemberDetailsService) principal;
            username = memberDetail.getUsername();
            role = memberDetail.getAuthorities().iterator().next().getAuthority();
        } else if (principal instanceof CustomOAuth2User){
            CustomOAuth2User oAuth2User = (CustomOAuth2User) principal;

            username = oAuth2User.getUsername();
            role = oAuth2User.getAuthorities().iterator().next().getAuthority();

        } else {
            throw  new IllegalStateException("Unexpected principal");
        }

        String token = jwtUtil.createJwt(username, role, 60 * 60 * 60L);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("/");
    }


    private Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
