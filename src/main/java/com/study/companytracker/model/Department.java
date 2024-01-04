package com.study.companytracker.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"employees","projects"})
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",unique = true,length = 64)
    private String name;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projects;
}
