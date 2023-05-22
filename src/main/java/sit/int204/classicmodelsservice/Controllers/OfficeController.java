package sit.int204.classicmodelsservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Office;
import sit.int204.classicmodelsservice.services.OfficeService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    @Autowired
    private OfficeService service;

    @GetMapping("")
    public List<Office> getOffices() {
        return service.getAllOffices();
    }

    @GetMapping("/{officeCode}")
    public Office getOffice(@PathVariable String officeCode) {
        return service.getOffice(officeCode);
    }

    @PostMapping("")
    public Office addNewOffice(@RequestBody Office office) {
        return service.addNewOffice(office);
    }

    @PutMapping("/{officeCode}")
    public Office updateOffice(@RequestBody Office office, @PathVariable String officeCode) {
        return service.updateOffice(officeCode, office);
    }

    @DeleteMapping("/{officeCode}")
    public void removeOffice(@PathVariable String officeCode) {
        service.removeOffice(officeCode);
    }

    @GetMapping("/{officeCode}/employees")
    public Set<Employee> getEmployeeByOffice(@PathVariable String officeCode){
       return service.getEmployeesByOfficeCode(officeCode);
    }

    @PostMapping("/{officeCode}/employees")
    public void addEmployeeByOffice(@PathVariable String officeCode, @RequestBody Employee employee){
        service.addEmployeeByOfficeCode(officeCode, employee);
    }
}
