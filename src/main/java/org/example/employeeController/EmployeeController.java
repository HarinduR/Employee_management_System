package org.example.employeeController;

import org.example.dataTransferObject.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emp-controller")
//http://localhost:8080/emp-controller/add-employee
public class EmployeeController {

    List<Employee> employeeList = new ArrayList();

    @PostMapping("/add-employee")

    public void addEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);

    }
}
