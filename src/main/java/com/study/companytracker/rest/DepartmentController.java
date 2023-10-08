package com.study.companytracker.rest;

import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.model.Department;
import com.study.companytracker.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> fetchAllDepartments() {
        return ResponseEntity.ok(this.departmentService.getAllDepartment());
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<GenericRestResponse<?>> fetchDepartmentByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(this.departmentService.getDepartmentByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GenericRestResponse<?>> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(this.departmentService.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentDTO.setId(0L);
        return ResponseEntity.ok(this.departmentService.addDepartment(departmentDTO));
    }

    @PutMapping
    public ResponseEntity<GenericRestResponse<?>> updateDepartment(@RequestBody DepartmentDTO department) {
        return ResponseEntity.ok(this.departmentService.addDepartment(department));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok(this.departmentService.deleteDepartmentById(id));
    }
}




















