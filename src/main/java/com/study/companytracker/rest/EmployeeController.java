package com.study.companytracker.rest;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.model.Employee;
import com.study.companytracker.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * @return ResponseEntity with GenericRestResponse includes all employees and response details
     * @Author Mo'men Magdy
     * Endpoint to fetch all employees
     */
    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    /**
     * @return ResponseEntity with GenericRestResponse includes all employees and response details
     * @Author Mo'men Magdy
     * Endpoint to fetch all employees within a department
     * @Param Department name
     */
    @GetMapping("/departmentName/{departmentName}")
    public ResponseEntity<GenericRestResponse<?>> getEmployeesByDepartmentName(@PathVariable String departmentName) {
        return ResponseEntity.ok(this.employeeService.getEmployeesByDepartmentName(departmentName));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GenericRestResponse<?>> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(this.employeeService.saveEmployee(employeeDTO));
    }
    @PutMapping
    public ResponseEntity<GenericRestResponse<?>> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(this.employeeService.saveEmployee(employeeDTO));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteEmployee(@PathVariable Long id){
        return ResponseEntity.ok(this.employeeService.deleteEmployeeById(id));
    }
}
