package com.study.companytracker.model;

import com.study.companytracker.util.enums.AdminRole;
import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email",unique = true,nullable = false,length = 128)
    private String email;

    @Column(name = "password",length = 1024,nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private AdminRole role;

    @Column(name = "session_id",length = 1024)
    private String sessionId;
}
