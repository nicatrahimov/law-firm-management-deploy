package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.dto.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<CompanyDto> getAllCompanies();
    CompanyDto getCompById(Long id);
    String addCompany(CompanyDto companyDto);
    String deleteCompanyById(Long id);
    String editCompany(CompanyDto companyDto,Long id);
}
