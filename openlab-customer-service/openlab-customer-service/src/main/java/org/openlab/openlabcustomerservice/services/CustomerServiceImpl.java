package org.openlab.openlabcustomerservice.services;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDto;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDto;
import org.openlab.openlabcustomerservice.entities.Customer;
import org.openlab.openlabcustomerservice.mappers.CustomerMapper;
import org.openlab.openlabcustomerservice.repositories.CustomerRepositories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepositories customerRepositories;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepositories customerRepositories, CustomerMapper customerMapper) {
        this.customerRepositories = customerRepositories;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        /*
           mapping objet objet
         */
       /* Customer customer=new Customer();
        customer.setId(customerRequestDto.getId());
        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        */
        Customer customer=customerMapper.customerRequestDtoToCustomer(customerRequestDto);
        //customer.setId(UUID.randomUUID().toString());
        Customer saveCustomer=customerRepositories.save(customer);

       /* CustomerResponseDto customerResponseDto=new CustomerResponseDto();
        customerResponseDto.setId(saveCustomer.getId());
        customerResponseDto.setName(saveCustomer.getName());
        customerResponseDto.setEmail(saveCustomer.getEmail());*/
        CustomerResponseDto customerResponseDto=customerMapper.customerToCustomerResponseDto(saveCustomer);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomer(String id) {
        Customer customer=customerRepositories.findById(id).get();//Optionnal
        return customerMapper.customerToCustomerResponseDto(customer) ;
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequestDto) {
        Customer customer=customerMapper.customerRequestDtoToCustomer(customerRequestDto);
        Customer updatedCustomer=customerRepositories.save(customer);
        return customerMapper.customerToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDto> listCustomers() {
        List<Customer> customers=customerRepositories.findAll();
        List<CustomerResponseDto> customerResponseDtos=
                customers.stream()
                        .map(cust ->customerMapper.customerToCustomerResponseDto(cust) )
                        .collect(Collectors.toList());
        return customerResponseDtos;
    }
}
