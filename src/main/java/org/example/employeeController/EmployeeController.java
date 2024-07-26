package org.example.employeeController;

import lombok.RequiredArgsConstructor;
import org.example.dataTransferObject.Employee;
import org.example.entity.EmployeeEntity;
import org.example.service.EmployeeService;
import org.example.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emp-controller")
//http://localhost:8080/emp-controller/add-employee
@RequiredArgsConstructor
public class EmployeeController {
    final EmployeeService service;
    @PostMapping("/add-employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        service.addEmployee(employee);
    }

    @GetMapping("get-all")
    public List<Employee> getAll() {
        return service.getAll();
    }

    @DeleteMapping("delete-emp/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return "Deleted";
    }

    @PostMapping("/update-emp")
    public void updateEmployee(@RequestBody Employee employee) {
        service.updateEmployee(employee);
    }
}
