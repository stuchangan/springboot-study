package com.javaboy.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * @author ChangAn
 * @date 2020/7/10 17:13
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest req =  (HttpServletRequest) servletRequest;
       //从请求头中获取token
        String jwtToken = req.getHeader("authorization");
        Jws<Claims> jws = Jwts.parser().setSigningKey("root@123")
                .parseClaimsJws(jwtToken.replace("Bearer", ""));
        Claims claims = jws.getBody();
        String username = claims.getSubject();
        //获取角色
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,"",authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
