package com.study.companytracker.security;

import com.study.companytracker.model.Admin;
import com.study.companytracker.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class RestInterceptor implements HandlerInterceptor{
    private static final Logger LOGGER= LoggerFactory.getLogger(RestInterceptor.class);

    private static Key hmacKey;

    private final AdminService adminService;

    @Value("${auth.secret}")
    private String secret;


    @PostConstruct
    private void securityInit() {
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        LOGGER.info("PreHandle Request");
        if (!request.getServletPath().contains("login")) {
            LOGGER.info("Not Authentication Request");
            if (request.getHeader("authToken") != null) {
                LOGGER.info("Token Found");
                String authToken = request.getHeader("authToken");
                try {
                    Jws<Claims> jwt = Jwts.parserBuilder()
                            .setSigningKey(hmacKey).build().parseClaimsJws(authToken);
                    if (jwt.getBody().get("email") != null) {
                        Admin admin = this.adminService.getAdminByEmail((String) jwt.getBody().get("email"));
                        if (admin != null) {
                            LOGGER.info("User Found");
                            boolean sameSessionId = admin.getSessionId().equals(authToken);
                            if(!sameSessionId) {
                                throw new AuthenticationException("No admin found for this token, please login again");
                            }
                            return true;
                        }
                        return false;
                    }
                } catch (Exception exception) {
                    LOGGER.info("Error While Extracting User Data");
                    throw new AuthenticationException("No admin found for this token, please login again");
                }
            } else {
                LOGGER.info("Token Not Found");
                throw new AuthenticationException("No access token found in the request");
            }
        } else {
            LOGGER.info("Authentication Request");
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
