package com.study.companytracker.repository.data;

import com.study.companytracker.model.Department;
import com.study.companytracker.repository.manager.DepartmentRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentData extends JpaRepository<Department,Long>, DepartmentRepo {

    Optional<Department> findDepartmentByName(String name);

    Optional<Department> findDepartmentById(Long id);

}
