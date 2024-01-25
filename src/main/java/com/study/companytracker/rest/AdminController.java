package com.study.companytracker.rest;


import com.study.companytracker.dto.AdminDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.LoginDTO;
import com.study.companytracker.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GenericRestResponse<?>> saveOrUpdateAdmin(@RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(this.adminService.saveOrUpdateAdmin(adminDTO));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteAdmin(@PathVariable(value = "id") Long adminId){
        return ResponseEntity.ok(this.adminService.deleteAdmin(adminId));
    }

    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> fetchAllAdmins(){
        return ResponseEntity.ok(this.adminService.fetchAllAdmins());
    }

    @PostMapping(path = "/validate-password")
    public ResponseEntity<GenericRestResponse<?>> validatePassword(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(this.adminService.validatePassword(loginDTO));
    }


}
