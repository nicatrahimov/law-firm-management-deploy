package com.rahimov.lawfirmmanagementdeploy.model;

import com.rahimov.lawfirmmanagementdeploy.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Table(name = "tasks")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @Enumerated(EnumType.STRING)
    Priority priority;


    String description;

    Integer remindDate;

    @ManyToOne
    @JoinColumn(name = "case_id") // Defines the foreign key column
    Case aCase;


}
