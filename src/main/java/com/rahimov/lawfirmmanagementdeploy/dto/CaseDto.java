package com.rahimov.lawfirmmanagementdeploy.dto;



import com.rahimov.lawfirmmanagementdeploy.enums.CaseStage;
import com.rahimov.lawfirmmanagementdeploy.enums.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CaseDto {
    Long id;
    String name;
    String caseNumber;
    String description;
    String openedDate;
    String limitDate;
    Office office;
    CaseStage caseStage;
}
