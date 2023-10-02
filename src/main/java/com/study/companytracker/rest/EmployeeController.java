package com.study.companytracker.rest;

import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Mo'men Magdy
 * Employee RestController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // Injecting Employee Service
    private final EmployeeService employeeService;


    /**
     * @Author Mo'men Magdy
     * Endpoint to fetch all employees
     * @return ResponseEntity with GenericRestResponse includes all employees and response details
     */
    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> getAllEmployees(){
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    /**
     * @Author Mo'men Magdy
     * Endpoint to fetch all employees within a department
     * @Param Department name
     * @return ResponseEntity with GenericRestResponse includes all employees and response details
     */
    @GetMapping("/{departmentName}")
    public ResponseEntity<GenericRestResponse<?>> getEmployeesByDepartmentName(@PathVariable String departmentName){
        return ResponseEntity.ok(this.employeeService.getEmployeesByDepartmentName(departmentName));
    }
}
