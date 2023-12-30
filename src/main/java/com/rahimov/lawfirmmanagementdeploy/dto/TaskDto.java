package com.rahimov.lawfirmmanagementdeploy.dto;


import com.rahimov.lawfirmmanagementdeploy.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    Long id;
    String name;
    Priority priority;
    String description;
    Integer remindDate;
    Long caseId;
}
