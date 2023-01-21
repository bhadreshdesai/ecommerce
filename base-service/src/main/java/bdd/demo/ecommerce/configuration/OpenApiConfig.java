package bdd.demo.ecommerce.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.MessageFormat;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomiser serverOpenApiCustomiser(@Value("${spring.application.name}") String appName, @Value("${app.description}") String appDesciption, @Value("${app.version}") String appVersion) {
        return openAPI -> {
            String title = MessageFormat.format("{0} API specification", appName);
            openAPI.info(new Info()
                    .title(title)
                    .version(appVersion)
                    .description(appDesciption)
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")));
            openAPI.getServers().forEach(server -> {
                server.setDescription(appName);
            });
        };
    }
}
