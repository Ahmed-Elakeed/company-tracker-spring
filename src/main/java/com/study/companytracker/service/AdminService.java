package com.study.companytracker.service;


import com.study.companytracker.converter.AdminConverter;
import com.study.companytracker.dto.*;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Admin;
import com.study.companytracker.repository.data.AdminData;
import com.study.companytracker.security.JwtTokenProvider;
import com.study.companytracker.util.enums.ErrorMessage;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminData adminData;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

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
                String jwtToken = this.jwtTokenProvider.generateToken(loginDTO);
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

    public GenericRestResponse<?> validatePassword(LoginDTO loginDTO) {
        Optional<Admin> optionalAdmin = this.adminData.findAdminByEmail(loginDTO.getEmail());
        if(!optionalAdmin.isPresent()){
            throw new NotFoundException("No Admin found with this email");
        }else{
            Admin admin=optionalAdmin.get();
            if(this.passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())){
                return GenericRestResponse.builder()
                        .data(true)
                        .responseMessage(ResponseMessage.SUCCESS)
                        .responseCode(ResponseMessage.SUCCESS.getCode())
                        .build();
            }else{
                throw new NotFoundException("Not valid password");
            }
        }
    }
}
