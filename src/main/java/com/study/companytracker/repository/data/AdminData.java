package com.study.companytracker.repository.data;

import com.study.companytracker.model.Admin;
import com.study.companytracker.repository.manager.AdminRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminData extends JpaRepository<Admin,Long>, AdminRepo {
    Optional<Admin> findAdminByEmail(@Param(value = "email") String email);
}
