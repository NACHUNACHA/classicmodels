package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(Integer employeeNumber) {
        return repository.findById(employeeNumber).orElseThrow(() -> new RuntimeException(employeeNumber + " does not exist !!!"));
    }

    public Employee addNewEmployee(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    public void removeEmployee(Integer employeeNumber) {
        Employee employee = repository.findById(employeeNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee Id " + employeeNumber + " DOES NOT EXIST !!!")
        );
        repository.delete(employee);
    }

    public Employee updateEmployee(Integer employeeNumber, Employee employee) {
        Employee existingEmployee = repository.findById(employeeNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Employee Id " + employeeNumber + " DOES NOT EXIST !!!")
        );
        existingEmployee.setExtension(employee.getExtension());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setJobTitle(employee.getJobTitle());
        return repository.saveAndFlush(existingEmployee);
    }

}
