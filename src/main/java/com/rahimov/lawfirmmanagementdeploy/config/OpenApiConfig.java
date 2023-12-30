package com.rahimov.lawfirmmanagementdeploy.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;


@OpenAPIDefinition(info = @Info(
        title = "Law Firm Management",
        description = "For final project",
        contact = @Contact(
                name = "Nicat Rahimov",
                email = "nijatrahimov9@gmail.com"
        )
),
        servers = {@Server(
                description = "LOCAL ENV",
                url = "http://localhost:8080"
        ),
                @Server(
                        description = "PROD ENV",
                        url = "-"
                )},
        tags = @Tag(name = "Tag name",
         description = "Tag description")
)

@SecurityScheme(
        name = "bearer Auth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
