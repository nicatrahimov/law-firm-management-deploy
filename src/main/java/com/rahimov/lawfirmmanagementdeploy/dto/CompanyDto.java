package com.rahimov.lawfirmmanagementdeploy.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyDto {

    Long id;
    String name;
    String email;
    String website;
    String phoneNumber;
    String address;
    String city;
    String country;
    String description;
}
