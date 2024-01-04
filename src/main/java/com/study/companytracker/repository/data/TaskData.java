package com.study.companytracker.repository.data;

import com.study.companytracker.model.Task;
import com.study.companytracker.repository.manager.TaskRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskData extends JpaRepository<Task,Long>, TaskRepo {
}
