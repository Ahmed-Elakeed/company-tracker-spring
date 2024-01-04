package com.study.companytracker.rest;


import com.study.companytracker.dto.AdminDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.LoginDTO;
import com.study.companytracker.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping
    @RequestMapping(path = "/login")
    public ResponseEntity<GenericRestResponse<?>> adminLogin(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(this.adminService.adminLogin(loginDTO));
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> addAdmin(@RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(this.adminService.saveNewAdmin(adminDTO));
    }


}
