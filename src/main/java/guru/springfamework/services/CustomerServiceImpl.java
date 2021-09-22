package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO= customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer/"+customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerbyFirstName(String name) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstName(name));
    }

    @Override
    public CustomerDTO getCustomerbyId(Long id ) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturn(customerMapper.customerDtoToCustomer(customerDTO));
//        Customer savedCustomer=customerRepository.save(customer);
//        CustomerDTO customerDTO1=customerMapper.customerToCustomerDTO(savedCustomer);
//        customerDTO1.setCustomerUrl("/api/v1/cutomers/"+savedCustomer.getId());
//        return customerDTO1;
    }
    private CustomerDTO saveAndReturn(Customer customer)
    {
        Customer savedCustomer=customerRepository.save(customer);
        CustomerDTO customerDTO1=customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO1.setCustomerUrl("/api/v1/cutomers/"+savedCustomer.getId());
        return customerDTO1;
    }

    @Override
    public CustomerDTO createCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer=customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturn(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName()!=null)
            {
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName()!=null)
            {
                customer.setLastName(customerDTO.getLastName());
            }
            CustomerDTO customerDTO1=customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            customerDTO1.setCustomerUrl("/api/v1/cutomers/"+id);
            return customerDTO1;
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
