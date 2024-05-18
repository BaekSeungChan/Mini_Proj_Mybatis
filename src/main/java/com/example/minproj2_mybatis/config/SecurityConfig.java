package com.example.minproj2_mybatis.config;

import com.example.minproj2_mybatis.auth.CustomSuccessHandler;
import com.example.minproj2_mybatis.auth.service.CustomOAuth2UserService;
import com.example.minproj2_mybatis.jwt.JWTFilter;
import com.example.minproj2_mybatis.jwt.JWTUtil;
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

    private final CustomSuccessHandler customSuccessHandler;

    private final JWTUtil jwtUtil;

    private final CustomOAuth2UserService customOAuth2UserService;
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

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);


        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );


        http.logout(logoutConfig ->
                        logoutConfig
                                .logoutUrl("/member/logout")
                                .logoutSuccessUrl("/")
                                .deleteCookies("Authorization")
        );

        http
                .oauth2Login(Customizer.withDefaults());

        http.authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .dispatcherTypeMatchers(DispatcherType.INCLUDE).permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/","/member/**", "/board/list","/volume-rank", "/finance/**","/gpt/**", "/admin/**","/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, "/auth/login"), UsernamePasswordAuthenticationFilter.class);

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
