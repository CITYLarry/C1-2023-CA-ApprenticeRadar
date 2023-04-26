package co.com.sofka.apprenticeradar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
        title = "Apprentice Radar API",
        version = "1.0.0",
        description = "API created for the agile challenge at SofkaU Bilingual Training League"
),      servers = {
        @Server(url = "https://c1-2023-ca-apprenticeradar-dev.up.railway.app/"),
        @Server(url = "https://c1-2023-ca-apprenticeradar-production.up.railway.app/"),
        @Server(url = "http://localhost:8080"),
})
public class ApprenticeRadarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprenticeRadarApplication.class, args);
    }
}
