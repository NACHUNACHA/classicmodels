package sit.int204.classicmodelsservice.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.dtos.PageDTO;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.services.CustomerService;
import sit.int204.classicmodelsservice.utils.ListMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public List<SimpleCustomerDTO> getAllCustomer() {
        List<Customer> customerList = service.getAllCustomer();
        return listMapper.mapList(customerList, SimpleCustomerDTO.class, modelMapper);
//        List<SimpleCustomerDTO> customerDTOList = new ArrayList(customerList.size());
//        for (Customer c : customerList) {
//            SimpleCustomerDTO sc = modelMapper.map(c, SimpleCustomerDTO.class);
//            customerDTOList.add(sc);
//        }
//        List<SimpleCustomerDTO> customerDTOList = customerList.stream().map(c -> modelMapper.map(c, SimpleCustomerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{customerId}")
    public SimpleCustomerDTO getSimpleCustomerById(@PathVariable Integer customerId) {
        return modelMapper.map(service.getCustomerById(customerId), SimpleCustomerDTO.class);
    }

    @GetMapping("/pages")
    public PageDTO<SimpleCustomerDTO> getCustomerWithPagination() {
        Page<Customer> customerList = service.getCustomerWithPagination(0, 5);
        return listMapper.toPageDTO(customerList, SimpleCustomerDTO.class, modelMapper);
    }
}
