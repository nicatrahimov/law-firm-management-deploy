package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomer();

    CustomerDto getById(Long id);

    String deleteById(Long id);

    String addCustomer(CustomerDto customerDto);

    String editCustomer(CustomerDto customerDto);

}
