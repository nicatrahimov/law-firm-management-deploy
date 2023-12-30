package com.rahimov.lawfirmmanagementdeploy.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
    String city;
    String country;
}
