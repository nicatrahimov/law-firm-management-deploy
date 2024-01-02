package com.rahimov.lawfirmmanagementdeploy.controller;

import com.rahimov.lawfirmmanagementdeploy.dto.CustomerDto;
import com.rahimov.lawfirmmanagementdeploy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    public ResponseEntity<List<CustomerDto>> getCustomers(){
        return new ResponseEntity<>(
                customerService.getAllCustomer(),
                HttpStatusCode.valueOf(200)
        );
    }
    public ResponseEntity<CustomerDto> getCustomerById(Long id){

        CustomerDto customer = customerService.getById(id);
        return new ResponseEntity<>(
                customer,
                HttpStatusCode.valueOf(200)
        );
    }


}
