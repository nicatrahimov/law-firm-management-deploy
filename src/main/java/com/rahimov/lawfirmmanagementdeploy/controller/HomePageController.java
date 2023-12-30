package com.rahimov.lawfirmmanagementdeploy.controller;


import com.rahimov.lawfirmmanagementdeploy.dto.CaseDto;
import com.rahimov.lawfirmmanagementdeploy.dto.CaseStatistic;
import com.rahimov.lawfirmmanagementdeploy.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomePageController {
    private final CaseService caseService;

    @GetMapping
    public CaseStatistic casesWithStatistic() {
        List<CaseDto> cases = caseService.getAllCases();
        int casesCount = cases.size();
int closedCount = 0;
int inProgressCount=0;
        for(CaseDto c:cases){
            if (c.getCaseStage().getDisplayName().equals("Closed")){
                closedCount++;
            }
            if (c.getCaseStage().getDisplayName().equals("In Progress")){
                inProgressCount++;
            }
        }
        return CaseStatistic.builder()
                .allCases(casesCount)
                .casesInProgress(inProgressCount)
                .closedCases(closedCount)
                .build();
    }


}
