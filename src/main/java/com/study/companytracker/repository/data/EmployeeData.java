package com.study.companytracker.repository.data;

import com.study.companytracker.model.Employee;
import com.study.companytracker.repository.manager.EmployeeRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeData extends JpaRepository<Employee, Long>, EmployeeRepo {

    Optional<List<Employee>> findEmployeesByDepartment_Name(String name);
}