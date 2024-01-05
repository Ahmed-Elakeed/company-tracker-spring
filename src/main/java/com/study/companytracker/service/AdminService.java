package com.study.companytracker.service;


import com.study.companytracker.converter.AdminConverter;
import com.study.companytracker.dto.*;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Admin;
import com.study.companytracker.repository.data.AdminData;
import com.study.companytracker.util.enums.ErrorMessage;
import com.study.companytracker.util.enums.ResponseMessage;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Value("${auth.secret}")
    private String secret;

    private static Key hmacKey;

    private final AdminData adminData;

    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    private void securityInit() {
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public GenericRestResponse<?> saveOrUpdateAdmin(AdminDTO adminDTO) {
        Admin updateCaseAdmin = null;
        if(adminDTO.getId()!=null) {
            updateCaseAdmin = this.adminData.findById(adminDTO.getId()).orElse(null);
        }
        Optional<Admin> optionalAdmin = this.adminData.findAdminByEmail(adminDTO.getEmail());
        if (optionalAdmin.isPresent() && updateCaseAdmin==null) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .responseMessage(ResponseMessage.FAIL)
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        } else {
            adminDTO.setPassword(this.passwordEncoder.encode(adminDTO.getPassword()));
            return GenericRestResponse.builder()
                    .data(this.adminData.save(AdminConverter.toEntity(adminDTO)))
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .responseMessage(ResponseMessage.SUCCESS)
                    .errorMessage(null)
                    .build();
        }
    }

    public GenericRestResponse<?> adminLogin(LoginDTO loginDTO) {
        Optional<Admin> optionalAdmin = this.adminData.findAdminByEmail(loginDTO.getEmail());
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (this.passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())) {
                String jwtToken = Jwts.builder()
                        .claim("email", loginDTO.getEmail())
                        .claim("password", loginDTO.getPassword())
                        .setSubject(loginDTO.getEmail())
                        .setId(UUID.randomUUID().toString())
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plus(5L, ChronoUnit.MINUTES)))
                        .signWith(hmacKey)
                        .compact();
                admin.setSessionId(jwtToken);
                this.adminData.save(admin);
                return GenericRestResponse.builder()
                        .data(AdminLoginResponseDTO.builder()
                                .id(admin.getId())
                                .token(jwtToken)
                                .email(admin.getEmail())
                                .role(admin.getRole().ordinal())
                                .name(admin.getName())
                                .build())
                        .responseMessage(ResponseMessage.SUCCESS)
                        .responseCode(ResponseMessage.SUCCESS.getCode())
                        .errorMessage(null)
                        .build();
            }
        }
        return GenericRestResponse.builder()
                .data(null)
                .responseCode(ResponseMessage.FAIL.getCode())
                .responseMessage(ResponseMessage.FAIL)
                .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                .build();
    }

    public GenericRestResponse<?> deleteAdmin(Long adminId) {
        Optional<Admin> department = this.adminData.findById(adminId);
        if (department.isPresent())
            this.adminData.deleteById(adminId);
        else
            throw new NotFoundException("No Admins Found with this Id: " + adminId);
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> fetchAllAdmins() {
        return GenericRestResponse.builder()
                .data(this.adminData.findAll().stream().map(AdminConverter::toDto))
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public Admin getAdminByEmail(String email) {
        return this.adminData.findAdminByEmail(email).orElse(null);
    }
}
