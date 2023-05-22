package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.exceptions.ResourceNotFoundException;
import sit.int204.classicmodelsservice.repository.EmployeeRepository;
import sit.int204.classicmodelsservice.repository.OfficeRepository;

import java.util.List;
import java.util.Set;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository repository;
    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Office> getAllOffices() {
        return repository.findAll();
    }

    public Office getOffice(String officeCode) {
        return repository.findById(officeCode).orElseThrow(() -> new ResourceNotFoundException(officeCode + " does not exist !!!"));
    }

    public Office addNewOffice(Office office) {
        return repository.saveAndFlush(office);
    }


    public void removeOffice(String officeCode) {
        Office office = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Office Id " + officeCode + " DOES NOT EXIST !!!")
        );
        repository.delete(office);
    }

    public Office updateOffice(String officeCode, Office office) {
        Office existingOffice = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Office Id " + officeCode + " DOES NOT EXIST !!!")
        );
        existingOffice.setCountry(office.getCountry());
        existingOffice.setAddressLine1(office.getAddressLine1());
        existingOffice.setAddressLine2(office.getAddressLine2());
        existingOffice.setPhone(office.getPhone());
        return repository.saveAndFlush(existingOffice);
    }

    public Set<Employee> getEmployeesByOfficeCode(String officeCode) {
        Office existingOffice = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Office Id " + officeCode + " DOES NOT EXIST")
        );
        return existingOffice.getEmployees();
    }

    public void addEmployeeByOfficeCode(String officeCode, Employee employee) {
        Office existingOffice = repository.findById(officeCode).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Office Id " + officeCode + " DOES NOT EXIST")
        );
        employee.setOffice(existingOffice);
        employeeRepo.saveAndFlush(employee);
        existingOffice.getEmployees().add(employee);
        repository.saveAndFlush(existingOffice);
    }
}
