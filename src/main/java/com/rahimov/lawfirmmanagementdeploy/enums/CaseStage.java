package com.rahimov.lawfirmmanagementdeploy.enums;

import lombok.Getter;

@Getter
public enum CaseStage {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    CLOSED("Closed");

    private final String displayName;

    CaseStage(String displayName) {
        this.displayName = displayName;
    }

}
