package com.rahimov.lawfirmmanagementdeploy.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rahimov.lawfirmmanagementdeploy.enums.CaseStage;
import com.rahimov.lawfirmmanagementdeploy.enums.Office;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;


import java.time.LocalDate;

import static com.rahimov.lawfirmmanagementdeploy.enums.Office.HEAD_OFFICE;

@Data
@Entity
@Table(name = "cases")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(name = "case_number")
    String caseNumber;

    @Column(name = "opened_date")
    LocalDate openedDate;

    @Column(name = "limit_date")
    LocalDate limitDate;

    String description;

    @Enumerated(EnumType.STRING)
    Office office;

    @Enumerated(EnumType.STRING)
    CaseStage caseStage;

    @ManyToOne
            @JoinColumn(name = "company_id")
    Company company;
}
