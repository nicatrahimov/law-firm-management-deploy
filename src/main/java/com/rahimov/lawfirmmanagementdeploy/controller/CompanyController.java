package com.rahimov.lawfirmmanagementdeploy.controller;


import com.rahimov.lawfirmmanagementdeploy.dto.CompanyDto;
import com.rahimov.lawfirmmanagementdeploy.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        return new
                ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id){
        CompanyDto compById = companyService.getCompById(id);
        return new ResponseEntity<>(compById,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addCompany( @RequestBody CompanyDto companyDto){
       companyService.addCompany(companyDto);
       return new ResponseEntity<>("Successfully",HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
return new
        ResponseEntity<>(companyService.deleteCompanyById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> editCompany(@RequestBody CompanyDto companyDto,
                                              @PathVariable Long id){
        return new
                ResponseEntity<>(companyService.editCompany(companyDto,id),HttpStatus.OK);
    }

}
