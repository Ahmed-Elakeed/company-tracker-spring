package com.study.companytracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.companytracker.util.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status",nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private Project project;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private Employee employee;
}
