package com.example.minproj2_mybatis.jwt;

import com.example.minproj2_mybatis.member.constant.Role;
import com.example.minproj2_mybatis.auth.entity.CustomMemberDetailsService;
import com.example.minproj2_mybatis.member.entity.MemberEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());

                if ("Authorization".equals(cookie.getName())) {
                    authorization = cookie.getValue();
                    break;  // Authorization 쿠키를 찾았으면 반복문을 종료합니다.
                }
            }
        }

        if (authorization == null) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;  // 조건이 해당되면 메소드 종료 ( 필수 )
        }

        String token = authorization;

        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // userEntity를 생성하여 값 set
        MemberEntity member = MemberEntity.builder()
                .name(username)
                .role(Role.valueOf(role))
                .build();

        // UserDetails에 회원 정보 객체 담기
        CustomMemberDetailsService customUserDetails = new CustomMemberDetailsService(member);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
