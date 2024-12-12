//package org.example.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.example.dataTransferObject.Employee;
//import org.example.entity.EmployeeEntity;
//import org.example.repository.EmployeeRepository;
//import org.example.service.EmployeeService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class EmployeeServiceImpl implements EmployeeService {
//
//
//
//    final EmployeeRepository repository;
//
//    @Override
//    public void addEmployee(Employee employee) {
//        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
//        repository.save(employeeEntity);
//
//    }
//
//    @Override
//    public List<Employee> getAll() {
//        List<Employee> employeeList = new ArrayList<>();
//        List<EmployeeEntity> allEntityList = repository.findAll();
//        ObjectMapper objectMapper = new ObjectMapper(); // Create ObjectMapper instance
//
//        allEntityList.forEach(employeeEntity -> {
//            employeeList.add(objectMapper.convertValue(employeeEntity, Employee.class)); // Use existing ObjectMapper instance
//        });
//
//        return employeeList;
//    }
//
//    @Override
//    public void deleteEmployeeById(Long id) {
//        if(repository.existsById(id)) {
//            repository.deleteById(id);
//        }
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//        if (repository.findById(employee.getEmpId()).isPresent()) {
//            ObjectMapper objectMapper = new ObjectMapper(); // Create ObjectMapper instance
//            EmployeeEntity entity = objectMapper.convertValue(employee, EmployeeEntity.class); // Correct conversion
//            repository.save(entity); // Save the entity
//        }
//    }
//
//    @Override
//    public Employee findById(Long id) {
//        return repository.findById(id).map(entity -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.convertValue(entity, Employee.class);
//        }).orElse(new Employee());
//    }
//
//    @Override
//    public Employee findByFirstName(String firstName) {
//        ObjectMapper objectMapper = new ObjectMapper(); // Correct spelling and initialization
//        EmployeeEntity entity = repository.findByFirstName(firstName); // Assuming this method returns an EmployeeEntity
//        return objectMapper.convertValue(entity, Employee.class); // Correct spelling and method usage
//    }
//
//
//
//}


package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dataTransferObject.Employee;
import org.example.entity.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Single ObjectMapper instance

    @Override
    public void addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = objectMapper.convertValue(employee, EmployeeEntity.class);
        repository.save(employeeEntity);
    }

    @Override
    public List<Employee> getAll() {
        // Map all EmployeeEntity objects to Employee DTOs
        return repository.findAll().stream()
                .map(entity -> objectMapper.convertValue(entity, Employee.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " does not exist.");
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Optional<EmployeeEntity> existingEntity = repository.findById(employee.getEmpId());
        if (existingEntity.isPresent()) {
            EmployeeEntity updatedEntity = objectMapper.convertValue(employee, EmployeeEntity.class);
            repository.save(updatedEntity);
        } else {
            throw new IllegalArgumentException("Employee with ID " + employee.getEmpId() + " does not exist.");
        }
    }

    @Override
    public Employee findById(Long id) {
        return repository.findById(id)
                .map(entity -> objectMapper.convertValue(entity, Employee.class))
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + id + " not found."));
    }

    @Override
    public Employee findByFirstName(String firstName) {
        EmployeeEntity entity = repository.findByFirstName(firstName); // Assuming this method is implemented
        if (entity == null) {
            throw new IllegalArgumentException("Employee with first name " + firstName + " not found.");
        }
        return objectMapper.convertValue(entity, Employee.class);
    }
}
