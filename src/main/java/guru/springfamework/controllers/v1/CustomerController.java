package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers()
    {
        return new CustomerListDTO(customerService.getAllCustomers());
//        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers())
//        , HttpStatus.OK);
    }
    @GetMapping("{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByName(@PathVariable String firstName)
    {
        return customerService.getCustomerbyFirstName(firstName);
//        return new ResponseEntity<CustomerDTO>(customerService.getCustomerbyFirstName(firstName),HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id)
    {
        return customerService.getCustomerbyId(id);
//        return new ResponseEntity<CustomerDTO>(customerService.getCustomerbyId(id),HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return customerService.createNewCustomer(customerDTO);
//        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO)
    {
        return customerService.createCustomerByDTO(id,customerDTO);
//        return new ResponseEntity<CustomerDTO>(customerService.createCustomerByDTO(id,customerDTO),HttpStatus.OK);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO)
    {
        return customerService.patchCustomer(id, customerDTO);
//        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id,customerDTO),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id)
    {
         customerService.deleteById(id);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id,customerDTO),HttpStatus.OK);
    }
}
