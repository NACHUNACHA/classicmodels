package sit.int204.classicmodelsservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{employeeNumber}")
    public Employee getEmployeeById(@PathVariable Integer employeeNumber) {
        return service.getEmployee(employeeNumber);
    }

    @PostMapping("")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return service.addNewEmployee(employee);
    }

    @PutMapping("/{employeeNumber}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeNumber) {
        return service.updateEmployee(employeeNumber, employee);
    }

    @DeleteMapping("/{employeeNumber}")
    public void removeEmployee(@PathVariable Integer employeeNumber) {
        service.removeEmployee(employeeNumber);
    }
}
