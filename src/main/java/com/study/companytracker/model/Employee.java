package com.study.companytracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"tasks"})
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false,length = 128)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id",referencedColumnName = "id",nullable = false)
    private Department department;


    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Task> tasks;

}