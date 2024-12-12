//package org.example.employeeController;
//
//import lombok.RequiredArgsConstructor;
//import org.example.dataTransferObject.Employee;
//import org.example.entity.EmployeeEntity;
//import org.example.service.EmployeeService;
//import org.example.service.impl.EmployeeServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/emp-controller")
////http://localhost:8080/emp-controller/add-employee
//@RequiredArgsConstructor
//public class EmployeeController {
//    final EmployeeService service;
//    @PostMapping("/add-employee")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addEmployee(@RequestBody Employee employee) {
//        service.addEmployee(employee);
//    }
//
//    @GetMapping("get-all")
//    public List<Employee> getAll() {
//        return service.getAll();
//    }
//
//    @DeleteMapping("delete-emp/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public String deleteEmployee(@PathVariable Long id) {
//        service.deleteEmployeeById(id);
//        return "Deleted";
//    }
//
//    @PostMapping("/update-emp")
//    public void updateEmployee(@RequestBody Employee employee) {
//        service.updateEmployee(employee);
//    }
//
//    @GetMapping("/find-by-id/{id}")
//    public Employee findById(@PathVariable Long id) {
//        return service.findById(id);
//    }
//
//    @GetMapping("/find-by-firstName/{firstName}")
//    public Employee findByFirstName(@PathVariable String firstName) {
//        return service.findByFirstName(firstName);
//    }
//}

package org.example.employeeController;

import lombok.RequiredArgsConstructor;
import org.example.dataTransferObject.Employee;
import org.example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
// Base URL will be http://localhost:8080/employees
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Endpoint to add a new employee
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    // Endpoint to get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    // Endpoint to delete an employee by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    // Endpoint to update an employee
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    // Endpoint to find an employee by ID
    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    // Endpoint to find an employee by first name
    @GetMapping("/search-by-firstName/{firstName}")
    public Employee findEmployeeByFirstName(@PathVariable String firstName) {
        return employeeService.findByFirstName(firstName);
    }
}

