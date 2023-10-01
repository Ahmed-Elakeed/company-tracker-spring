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

    @Override
    public Department fetchDepartmentById(Long id) {
//        Query query = entityManager.createQuery("select d from Department d where d.id =:id");
//        query.setParameter("id",id);
//        Department department = (Department) query.getSingleResult();
        return (Department) this.entityManager.createQuery("select d from Department d where d.id=:id").setParameter("id", id).getSingleResult();
    }

}
