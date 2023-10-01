package com.study.companytracker.repository.manager;

import com.study.companytracker.model.Department;

import java.util.List;

public interface DepartmentRepo {

    List<Department> fetchAllDepartments();

    Department fetchDepartmentById(Long id);

}
