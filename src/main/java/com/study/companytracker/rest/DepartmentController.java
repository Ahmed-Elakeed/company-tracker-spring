package com.study.companytracker.rest;

import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.service.DepartmentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @RateLimiter(name = "default")
    public ResponseEntity<GenericRestResponse<?>> fetchAllDepartments(@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.departmentService.getAllDepartment());
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> addDepartment(@RequestBody DepartmentDTO departmentDTO,@RequestHeader(value = "authToken") String authToken) {
        departmentDTO.setId(0L);
        return ResponseEntity.ok(this.departmentService.addDepartment(departmentDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteDepartmentById(@PathVariable Long id,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.departmentService.deleteDepartmentById(id));
    }

    @GetMapping(path = "/{departmentId}/employees")
    public ResponseEntity<GenericRestResponse<?>> fetchDepartmentEmployees(@PathVariable(value = "departmentId") Long departmentId,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.departmentService.fetchDepartmentEmployees(departmentId));
    }

    @GetMapping(path = "/{departmentId}/projects")
    public ResponseEntity<GenericRestResponse<?>> fetchDepartmentProjects(@PathVariable(value = "departmentId") Long departmentId,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.departmentService.fetchDepartmentProjects(departmentId));
    }
}




















