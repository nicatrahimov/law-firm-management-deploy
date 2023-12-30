package com.rahimov.lawfirmmanagementdeploy.service.Impl;


import com.rahimov.lawfirmmanagementdeploy.dto.CaseDto;
import com.rahimov.lawfirmmanagementdeploy.exception.CaseNotFoundException;
import com.rahimov.lawfirmmanagementdeploy.model.Case;
import com.rahimov.lawfirmmanagementdeploy.repository.CaseRepository;
import com.rahimov.lawfirmmanagementdeploy.service.CaseService;
import com.rahimov.lawfirmmanagementdeploy.util.DateConverter;
import com.rahimov.lawfirmmanagementdeploy.util.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;

    @Override
    public List<CaseDto> getAllCases() {
        List<Case> cases = caseRepository.findAll();
        List<CaseDto>caseDtos=new ArrayList<>();
        for (Case aCase:
             cases) {
            CaseDto caseDto = CaseDto.builder()
                    .id(aCase.getId())
                    .name(aCase.getName())
                    .caseNumber(aCase.getCaseNumber())
                    .description(aCase.getDescription())
                    .office(aCase.getOffice())
                    .caseStage(aCase.getCaseStage())
                    .openedDate(DateConverter.localDateToString(aCase.getOpenedDate()))
                    .limitDate(DateConverter.localDateToString(aCase.getLimitDate()))
                    .build();
            caseDtos.add(caseDto);
        }
        return caseDtos;
    }

    @Override
    public CaseDto getCaseById(Long id) {

        Case aCase = caseRepository.findById(id).orElseThrow(() -> new CaseNotFoundException("Case not found id with: " + id));

       return CaseDto.builder()
                .id(aCase.getId())
                .caseNumber(aCase.getCaseNumber())
                .name(aCase.getName())
                .office(aCase.getOffice())
                .caseStage(aCase.getCaseStage())
                .limitDate(DateConverter.localDateToString(aCase.getLimitDate()))
                .openedDate(DateConverter.localDateToString(aCase.getOpenedDate()))
                .description(aCase.getDescription())
                .build();
    }

    @Override
    public String addCase(CaseDto caseDto) {

        Case aCase = Case.builder()
                .name(caseDto.getName())
                .caseNumber(caseDto.getCaseNumber())
                .description(caseDto.getDescription())
                .limitDate(DateConverter.toLocalDate(caseDto.getLimitDate()))
                .openedDate(DateConverter.toLocalDate(caseDto.getOpenedDate()))
                .caseStage(caseDto.getCaseStage())
                .office(caseDto.getOffice())
                .build();
        if (NullChecker.isNotNull(aCase)){
            caseRepository.save(aCase);
        }
        return "added successfully";

    }

    @Override
    public String deleteCaseById(Long id) {
        Case aCase = caseRepository.findById(id).orElseThrow(() -> new CaseNotFoundException("Case not found id with: " + id));
        caseRepository.delete(aCase);
        return "deleted successfully";

    }

    @Override
    public String editCase(CaseDto caseDto) {
        Case aCase = caseRepository.findById(caseDto.getId()).orElseThrow(() -> new CaseNotFoundException("Case not found id with: " + caseDto.getId()));

        if (NullChecker.isNotNull(caseDto.getName())) {
            aCase.setName(caseDto.getName());
        }
        if (NullChecker.isNotNull(caseDto.getCaseNumber())) {
            aCase.setCaseNumber(caseDto.getCaseNumber());
        }
        if (NullChecker.isNotNull(caseDto.getDescription())) {
            aCase.setDescription(caseDto.getDescription());
        }
        if (NullChecker.isNotNull(caseDto.getOpenedDate())) {
            aCase.setOpenedDate(DateConverter.toLocalDate(caseDto.getOpenedDate()));
        }
        if (NullChecker.isNotNull(caseDto.getLimitDate())) {
            aCase.setLimitDate(DateConverter.toLocalDate(caseDto.getLimitDate()));
        }
        if (NullChecker.isNotNull(caseDto.getCaseStage())) {
            aCase.setCaseStage(caseDto.getCaseStage());
        }
        if (NullChecker.isNotNull(caseDto.getOffice())) {
            aCase.setOffice(caseDto.getOffice());
        }
        caseRepository.save(aCase);
        return "edited successfully";
    }
}
