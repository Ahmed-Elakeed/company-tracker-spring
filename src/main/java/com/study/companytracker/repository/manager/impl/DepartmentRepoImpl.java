package com.study.companytracker.repository.manager.impl;

import com.study.companytracker.model.Department;
import com.study.companytracker.repository.manager.DepartmentRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DepartmentRepoImpl implements DepartmentRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> fetchAllDepartments() {
        return this.entityManager.createQuery("select d from Department d").getResultList();
    }
}
