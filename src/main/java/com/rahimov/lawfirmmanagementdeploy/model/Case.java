package com.rahimov.lawfirmmanagementdeploy.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rahimov.lawfirmmanagementdeploy.enums.CaseStage;
import com.rahimov.lawfirmmanagementdeploy.enums.Office;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Column(name = "opened_date")
    LocalDate openedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Column(name = "limit_date")
    LocalDate limitDate;

    String description;

    @Enumerated(EnumType.STRING)
    Office office;

    @Enumerated(EnumType.STRING)
    CaseStage caseStage;
}
