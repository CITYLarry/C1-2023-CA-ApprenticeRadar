package co.com.sofka.apprenticeradar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Apprentice Radar API",
        version = "1.0.0",
        description = "API created for the agile challenge at SofkaU Bilingual Training League"
))

public class ApprenticeRadarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprenticeRadarApplication.class, args);
    }

}

