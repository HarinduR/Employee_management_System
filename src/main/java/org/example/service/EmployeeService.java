package org.example.service;

import org.example.dataTransferObject.Employee;
import org.example.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {


    default void addEmployee(Employee employee){

    }

    List<EmployeeEntity> getAll();
}