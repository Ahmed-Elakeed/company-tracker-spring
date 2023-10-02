package com.study.companytracker.rest;

import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> getAllEmployees(){
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }
    @GetMapping("/{departmentName}")
    public ResponseEntity<GenericRestResponse<?>> getEmployeesByDepartmentName(@PathVariable String departmentName){
        return ResponseEntity.ok(this.employeeService.getEmployeesByDepartmentName(departmentName));
    }
}
