package org.team_project.uni_lodz_park_area.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Configuration class named {@link OpenApiConfig} for OpenAPI documentation.
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mehmet Yuzen | Mustafa Capras | Michał Małagowski | Mykola Horbenko | Veronika Kobets | Buse Iyigun | Dongchen Luo | Ravshanjon Muminov",
                        url = "https://github.com/mehmetseymusyuzen/TP34_sego/"
                ),
                description = "University of Lodz Park Area " +
                        "University of Lodz Park Area is a Spring Boot application covering important and useful features related to managing parking areas, park check-ins and check-outs for drivers, user management, and vehicle management. ",
                title = "University of Lodz Park Area",
                version = "1.0.0"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
