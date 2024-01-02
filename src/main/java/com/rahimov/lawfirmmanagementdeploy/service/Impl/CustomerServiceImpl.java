package com.rahimov.lawfirmmanagementdeploy.service.Impl;


import com.rahimov.lawfirmmanagementdeploy.dto.CustomerDto;
import com.rahimov.lawfirmmanagementdeploy.exception.CustomerNotFoundException;
import com.rahimov.lawfirmmanagementdeploy.model.Customer;
import com.rahimov.lawfirmmanagementdeploy.repository.CustomerRepository;
import com.rahimov.lawfirmmanagementdeploy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> dtos = new ArrayList<>();

        for (Customer customer :
                customers) {
            CustomerDto dto = CustomerDto.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .email(customer.getEmail())
                    .address(customer.getAddress())
                    .city(customer.getCity())
                    .phoneNumber(customer.getPhoneNumber())
                    .country(customer.getCountry())
                    .build();

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return
                CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .email(customer.getEmail())
                        .address(customer.getAddress())
                        .city(customer.getCity())
                        .phoneNumber(customer.getPhoneNumber())
                        .country(customer.getCountry())
                        .build();
    }

    @Override
    public String deleteById(Long id) {
       customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
            customerRepository.deleteById(id);
            return "Successfull";
    }

    @Override
    public String addCustomer(CustomerDto customerDto) {
       Customer customer = Customer.builder()
               .email(customerDto.getEmail())
               .firstName(customerDto.getFirstName())
               .lastName(customerDto.getLastName())
               .phoneNumber(customerDto.getPhoneNumber())
               .email(customerDto.getEmail())
               .city(customerDto.getCity())
               .country(customerDto.getCountry())
               .build();

       customerRepository.save(customer);

       return "Successfully";

    }

    @Override
    public String editCustomer(CustomerDto customerDto) {
        try{
            Customer customer1 = customerRepository.findById(customerDto.getId()).orElseThrow(CustomerNotFoundException::new);
           customer1.setAddress(customerDto.getAddress());
           customer1.setCity(customerDto.getCity());
           customer1.setCountry(customerDto.getCountry());
           customer1.setFirstName(customerDto.getFirstName());
           customer1.setLastName(customerDto.getLastName());
           customer1.setPhoneNumber(customerDto.getPhoneNumber());
           customer1.setEmail(customerDto.getEmail());
           customerRepository.save(customer1);
           return "Successfully";
        }catch (CustomerNotFoundException e){
            return e.getMessage();
        }
    }
}
