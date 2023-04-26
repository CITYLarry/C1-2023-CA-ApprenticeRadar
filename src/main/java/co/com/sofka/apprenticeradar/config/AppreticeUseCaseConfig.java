package co.com.sofka.apprenticeradar.config;

import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.delete.DeleteApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getall.GetAllApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyemail.GetApprenticeByEmailUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyid.GetApprenticeByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.save.SaveApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.update.UpdateApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.delete.DeleteTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getall.GetAllTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getbyid.GetTrainingByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.save.SaveTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.update.UpdateTrainingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.apprenticeradar.domain.usecase.apprentice")
public class AppreticeUseCaseConfig {
    @Bean
    public GetAllApprenticeUseCase getAllApprenticeUseCase(ApprenticeGateway gateway) {
        return new GetAllApprenticeUseCase(gateway);
    }

    @Bean
    public GetApprenticeByIdUseCase getApprenticeByIdUseCase(ApprenticeGateway gateway) {
        return new GetApprenticeByIdUseCase(gateway);
    }

    @Bean
    public SaveApprenticeUseCase saveApprenticeUseCase(ApprenticeGateway gateway) {
        return new SaveApprenticeUseCase(gateway);
    }

    @Bean
    public UpdateApprenticeUseCase updateApprenticeUseCase(ApprenticeGateway gateway) {
        return new UpdateApprenticeUseCase(gateway);
    }

    @Bean
    public DeleteApprenticeUseCase deleteApprenticeUseCase(ApprenticeGateway gateway) {
        return new DeleteApprenticeUseCase(gateway);
    }

    @Bean
    public GetApprenticeByEmailUseCase getApprenticeByEmailUseCase(ApprenticeGateway gateway) {
        return new GetApprenticeByEmailUseCase(gateway);
    }
}
