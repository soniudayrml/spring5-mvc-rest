package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);
//@Mapping(source = "id",target = "id")
    CustomerDTO customerToCustomerDTO(Customer customer);

//    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
