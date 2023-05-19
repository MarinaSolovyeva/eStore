package com.example.estote.config;

import com.example.estote.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailService userDetailService;

    @Autowired
    public SecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/admin", "/adminInfo").hasRole("ADMIN")
                .requestMatchers("/auth/login", "/auth/registration", "/error", "/", "/catalog").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
        return http.build();}

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http,
        BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailService)
                    .passwordEncoder(bCryptPasswordEncoder)
                    .and()
                    .build();
        }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
