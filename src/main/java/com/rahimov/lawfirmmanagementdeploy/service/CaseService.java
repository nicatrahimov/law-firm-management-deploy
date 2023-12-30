package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.dto.CaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseService {

    List<CaseDto> getAllCases();
    CaseDto getCaseById(Long id);
    String addCase(CaseDto caseDto);
    String deleteCaseById(Long id);
    String editCase(CaseDto caseDto);
}
