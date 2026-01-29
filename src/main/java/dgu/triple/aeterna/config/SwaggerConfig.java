package dgu.triple.aeterna.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Aeterna API")
                .description("Aeterna backend OpenAPI documentation")
                .version("v1.0.0")
                .contact(new Contact().name("Backend Team"));

//        List<Server> servers = List.of(
//                new Server().url("http://localhost:8080").description("local")
//        );

        return new OpenAPI()
                .info(info);
    }
}
