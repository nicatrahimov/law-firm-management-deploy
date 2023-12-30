package com.rahimov.lawfirmmanagementdeploy.service.Impl;


import com.rahimov.lawfirmmanagementdeploy.dto.CustomerDto;
import com.rahimov.lawfirmmanagementdeploy.exception.CustomerNotFoundException;
import com.rahimov.lawfirmmanagementdeploy.model.Customer;
import com.rahimov.lawfirmmanagementdeploy.repository.CustomerRepository;
import com.rahimov.lawfirmmanagementdeploy.service.CustomerService;
import com.rahimov.lawfirmmanagementdeploy.util.NullChecker;
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
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        if (NullChecker.isNull(customer)){
            return "No customer with id: "+id;
        }else {
            customerRepository.deleteById(id);
            return "Successfull";
        }

    }

    @Override
    public String addCustomer(CustomerDto customerDto) {
        return "ahsdbasnd";
    }

    @Override
    public String editCustomer(CustomerDto customerDto) {
        return "ahsbda";
    }
}
