package com.rahimov.lawfirmmanagementdeploy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GrantRequest {

    @NotBlank
    private String token;

}
