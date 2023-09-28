package com.study.companytracker.rest;

import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.DepartmentService;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> fetchAllDepartments(){
        return ResponseEntity.ok(this.departmentService.getAllDepartment());
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<GenericRestResponse<?>> fetchDepartmentByName(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(this.departmentService.getDepartmentByName(name));
    }
}
