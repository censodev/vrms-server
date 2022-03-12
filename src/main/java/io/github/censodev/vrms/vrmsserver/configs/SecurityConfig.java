package io.github.censodev.vrms.vrmsserver.configs;

import com.censodev.jauthutils.jwt.JwtAuthenticationFilter;
import com.censodev.jauthutils.jwt.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.censodev.vrms.vrmsserver.data.models.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${auth.secret}")
    private String authSecret;

    private final ObjectMapper objectMapper;

    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public TokenProvider getTokenProvider() {
        return TokenProvider.builder()
                .expiration(86_400_000)
                .secret(authSecret)
                .mapper(objectMapper)
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter<>(getTokenProvider(), Account.class), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/auth/otp", "/api/auth/login", "/api/auth/admin/login").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();
    }
}
