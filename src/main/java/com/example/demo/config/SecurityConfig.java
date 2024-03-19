package com.example.demo.config;

import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final UserService userService;
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //rest api -> session 기반 x , 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterAfter(jsonUserAuthFilter(), LogoutFilter.class)
                .authorizeHttpRequests((authorise) -> authorise
                        //로그인, 회원가입은 권한없이 접근할 수 있도록 허용
                        .requestMatchers("/user/add", "/user/login").permitAll()
                        .anyRequest().authenticated())
                .logout((logout)->logout
                        .logoutSuccessUrl("/user/login")
                        //로그아웃 시 세션 x
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();

    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // 인증 관리자 관련 설정
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {//AuthenticationManager 등록
        DaoAuthenticationProvider provider = daoAuthenticationProvider();//DaoAuthenticationProvider 사용
        provider.setPasswordEncoder(passwordEncoder());//PasswordEncoder로는 PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용
        return new ProviderManager(provider);
    }
    @Bean
    public JsonUserAuthFilter jsonUserAuthFilter() throws Exception {
        JsonUserAuthFilter jsonUserAuthFilter = new JsonUserAuthFilter(objectMapper);
        jsonUserAuthFilter.setAuthenticationManager(authenticationManager());
        return jsonUserAuthFilter;
    }
}
