package com.javaboy.jwt.config;

import com.javaboy.jwt.filter.JwtFilter;
import com.javaboy.jwt.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ChangAn
 * @date 2020/7/10 15:50
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置默认用户名密码，在内存中加载
        auth.inMemoryAuthentication()
                .withUser("root").password("$2a$10$6Ug6x/8WETqNYmQ/1s.wDe3qyAj9D.HvnSsjY2FCjDxvfIhbWXoCm")
                .roles("admin")
                .and()
                .withUser("hello").password("$2a$10$M1c9z1ZQj7dHVTQfvRAHhepnMAge7ZgRQn9VGfi9ifTJtNK7fpaPG")
                .roles("user");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启认证，
        http.authorizeRequests()
                .antMatchers("/hello")
                .hasRole("user")
                .antMatchers("/admin")
                .hasRole("admin")
                .antMatchers(HttpMethod.POST,"/login")
                //表示哪些请求可以直接放行
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

}
