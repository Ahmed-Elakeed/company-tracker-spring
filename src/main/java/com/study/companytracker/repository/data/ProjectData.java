package com.study.companytracker.repository.data;

import com.study.companytracker.model.Project;
import com.study.companytracker.repository.manager.ProjectRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectData extends JpaRepository<Project,Long>, ProjectRepo {
}
