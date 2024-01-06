package com.study.companytracker.repository.manager.impl;

import com.study.companytracker.dto.TaskReportDTO;
import com.study.companytracker.repository.manager.TaskRepo;
import com.study.companytracker.util.enums.ProjectStatus;
import com.study.companytracker.util.enums.TaskStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepoImpl implements TaskRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<TaskReportDTO> fetchTasksReportData() {
        String queryString = "select d.name as departmentName," +
                "p.name as projectName," +
                "p.status as projectStatus," +
                "e.fullName as employeeName," +
                "t.name as taskName," +
                "t.startDate as taskStartDate," +
                "t.endDate as taskEndDate," +
                "t.status as taskStatus" +
                " from Task t left join fetch Employee e " +
                "on e.id=t.employee.id " +
                "left join fetch Project p " +
                "on p.id=t.project.id " +
                "left join fetch Department d " +
                "on d.id=p.department.id";

        Query query = this.entityManager.createQuery(queryString);

        List<Object[]> resultList = query.getResultList();

        return resultList.stream()
                .map(row -> TaskReportDTO.builder()
                        .departmentName((String) row[0])
                        .projectName((String) row[1])
                        .projectStatus((ProjectStatus) row[2])
                        .employeeName((String) row[3])
                        .taskName((String) row[4])
                        .taskStartDate((Date) row[5])
                        .taskEndDate((Date) row[6])
                        .taskStatus((TaskStatus) row[7])
                        .build()
                ).collect(Collectors.toList());
    }
}
