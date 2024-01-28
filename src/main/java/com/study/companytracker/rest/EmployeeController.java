package com.study.companytracker.rest;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> getAllEmployees(@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteEmployeeById(@PathVariable(value = "id") Long id,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.employeeService.deleteEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> saveOrUpdateEmployee(@RequestBody EmployeeDTO employeeDTO,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.employeeService.saveOrUpdateEmployee(employeeDTO));
    }
}
