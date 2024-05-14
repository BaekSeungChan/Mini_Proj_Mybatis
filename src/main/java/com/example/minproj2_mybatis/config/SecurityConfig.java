package com.example.minproj2_mybatis.config;

import com.example.minproj2_mybatis.jwt.LoginFilter;
import com.example.minproj2_mybatis.member.service.MemberService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable())
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable());

//        http.formLogin(form -> form
//                .loginPage("/member/login")  // 사용자 정의 로그인 페이지 경로 설정
////                .successHandler(customAuthentiactionSuccesHandler)
////                .defaultSuccessUrl("/board/list")  // 로그인 성공 시 리다이렉트 될 기본 URL 설정
//                .failureUrl("/member/login/error")  // 로그인 실패 시 리다이렉트 될 URL 설정
//                .usernameParameter("email")  // 로그인 폼에서 사용할 사용자 이름 파라미터 이름 설정
//                .passwordParameter("password")  // 로그인 폼에서 사용할 비밀번호 파라미터 이름 설정
//                .permitAll());  // 모든 사용자가 접근할 수 있도록 설정

        http.logout(logoutConfig ->
                        logoutConfig
                                .logoutUrl("/member/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID")
//                        .logoutSuccessHandler().
//                        .addLogoutHandler()
        );

        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/","/member/**", "/board/list","/volume-rank", "/finance/**","/gpt/**", "/admin/**").permitAll()
                .requestMatchers("/admin/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated());

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);


        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
