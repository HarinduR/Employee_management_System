//package org.example.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Entity
//@Table(name = "employee")
//public class EmployeeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long empId;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String departmentId;
//    private String roleId;
//
//    @OneToMany
//    @OneToOne
//    @ManyToOne
//
//}


package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId; // Employee ID (Primary Key)

    @Column(nullable = false)
    private String firstName; // First Name of the Employee

    @Column(nullable = false)
    private String lastName; // Last Name of the Employee

    @Column(nullable = false, unique = true)
    private String email; // Email (Must be unique)

    @Column(nullable = false)
    private String departmentId; // Department ID (Foreign Key)

    @Column(nullable = false)
    private String roleId; // Role ID (Foreign Key)
}
