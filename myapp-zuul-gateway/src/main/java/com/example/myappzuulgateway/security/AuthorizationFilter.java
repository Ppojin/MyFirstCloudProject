package com.example.myappzuulgateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Environment env;

    @Autowired
    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
        super(authenticationManager);
        this.env = env;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // header 에 token 포함 여부 및 올바른 token 인지
        String authHeader = request.getHeader(env.getProperty("authorization.token.header.name"));
        String prefix = env.getProperty("authorization.token.header.prefix");
        if(authHeader == null || !authHeader.startsWith(prefix)){
            chain.doFilter(request, response);
            return;
        }
        String token = authHeader.replace(prefix, "");

        // UsernamePasswordAuthenticationToken 가져오기
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        // 사용자 요청 페이지로 이동
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String authHeader = request.getHeader(env.getProperty("authorization.token.header.name"));

        if(authHeader == null){// 받은 토큰이 없거나
            return null;
        }
        String token = authHeader.replace(env.getProperty("authorization.token.header.prefix"),"");
        String userId = Jwts.parser()
                .setSigningKey(env.getProperty("token.secret"))
                .parseClaimsJws(token.trim())
                .getBody()
                .getSubject();
        if(userId == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
