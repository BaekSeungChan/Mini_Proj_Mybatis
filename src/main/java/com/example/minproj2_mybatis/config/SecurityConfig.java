package com.example.minproj2_mybatis.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http  // 기존 security에서 제공해주는 login 및 logout 사용
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults());

        http
                .csrf((auth) -> auth.disable())
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable());


        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/common/**").permitAll()
                .requestMatchers("/","/member/**", "/board/**", "/member/new").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());


        http.logout(Customizer.withDefaults());


//        http.exceptionHandling(exception -> exception
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
//                .accessDeniedHandler("/accessDenied"); // 예외 페이지를 제공해줘도 된다.


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
