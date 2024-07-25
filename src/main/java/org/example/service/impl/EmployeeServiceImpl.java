package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dataTransferObject.Employee;
import org.example.entity.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {



    final EmployeeRepository repository;

    @Override
    public void addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        repository.save(employeeEntity);

    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeEntity> allEntityList = repository.findAll();
        ObjectMapper objectMapper = new ObjectMapper(); // Create ObjectMapper instance

        allEntityList.forEach(employeeEntity -> {
            employeeList.add(objectMapper.convertValue(employeeEntity, Employee.class)); // Use existing ObjectMapper instance
        });

        return employeeList;
    }

}
