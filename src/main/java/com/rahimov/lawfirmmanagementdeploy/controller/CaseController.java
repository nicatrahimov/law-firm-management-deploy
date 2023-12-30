package com.rahimov.lawfirmmanagementdeploy.controller;


import com.rahimov.lawfirmmanagementdeploy.dto.CaseDto;
import com.rahimov.lawfirmmanagementdeploy.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
public class CaseController {


    private final CaseService caseService;


    @GetMapping
    public ResponseEntity<List<CaseDto>> getAllCases() {
return new
        ResponseEntity<>(caseService.getAllCases(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDto> getCaseById(@PathVariable("id") Long id) {
        return new
                ResponseEntity<>(caseService.getCaseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCase(@RequestBody CaseDto caseDto) {
        return new
                ResponseEntity<>(caseService.addCase(caseDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String>editCase(@RequestBody CaseDto caseDto){
        return new
                ResponseEntity<>(caseService.editCase(caseDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCase(@PathVariable("id") Long id){
        return new
                ResponseEntity<>(caseService.deleteCaseById(id),HttpStatus.OK);
    }

}
