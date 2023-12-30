package com.rahimov.lawfirmmanagementdeploy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
     @NotBlank
     String firstName;
     @NotBlank
     String lastName;
     @NotBlank
     String username;
     @NotBlank
     String password;
     @NotBlank
     String email;
}