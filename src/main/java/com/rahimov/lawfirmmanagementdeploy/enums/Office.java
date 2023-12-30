package com.rahimov.lawfirmmanagementdeploy.enums;

import lombok.Getter;

@Getter
public enum Office {
    HEAD_OFFICE("Head Office"),
    BRANCH_OFFICE("Branch Office"),
    REGIONAL_OFFICE("Regional Office");

    private final String displayName;

    Office(String displayName) {
        this.displayName = displayName;
    }
}
