package com.study.companytracker.security;

import com.google.gson.Gson;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.util.enums.ResponseMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        TokenStatus tokenStatus = jwtTokenProvider.validateToken(token);
        if (token != null && tokenStatus.equals(TokenStatus.VALID)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } else {
            String errorMessage = tokenStatus.equals(TokenStatus.ERROR) ? "Invalid or empty token":"Token Expired, please login again";
            GenericRestResponse<?> genericResponse = GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.AUTHENTICATION_FAILURE)
                    .responseCode(ResponseMessage.AUTHENTICATION_FAILURE.getCode())
                    .errorMessage(errorMessage)
                    .build();
            String jsonResponse = convertToJson(genericResponse);
            response.setContentType("application/json");
            try (PrintWriter writer = response.getWriter()) {
                writer.write(jsonResponse);
            }
        }
    }

    private String convertToJson(GenericRestResponse<?> genericResponse) {
        Gson gson = new Gson();
        return gson.toJson(genericResponse);
    }
}

