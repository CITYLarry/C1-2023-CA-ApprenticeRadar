package co.com.sofka.apprenticeradar.config;

import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.domain.usecase.radar.delete.DeleteRadarUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.getall.GetAllRadarsUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.getbyid.GetRadarByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.save.SaveRadarUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.update.UpdateRadarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.apprenticeradar.domain.usecase.radar")
public class RadarUseCasesConfig {

    @Bean
    public GetAllRadarsUseCase getAllRadarsUseCase(RadarGateway gateway) {
        return new GetAllRadarsUseCase(gateway);
    }

    @Bean
    public GetRadarByIdUseCase getRadarByIdUseCase(RadarGateway gateway) {
        return new GetRadarByIdUseCase(gateway);
    }

    @Bean
    public SaveRadarUseCase saveRadarUseCase(RadarGateway gateway) {
        return new SaveRadarUseCase(gateway);
    }

    @Bean
    public UpdateRadarUseCase updateRadarUseCase(RadarGateway gateway) {
        return new UpdateRadarUseCase(gateway);
    }

    @Bean
    public DeleteRadarUseCase deleteRadarUseCase(RadarGateway gateway) {
        return new DeleteRadarUseCase(gateway);
    }
}
