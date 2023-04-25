package co.com.sofka.apprenticeradar.config;

import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import co.com.sofka.apprenticeradar.domain.usecase.training.delete.DeleteTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getall.GetAllTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getbyid.GetTrainingByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.save.SaveTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.update.UpdateTrainingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.apprenticeradar.domain.usecase.training")
public class TrainingUseCasesConfig {

    @Bean
    public GetAllTrainingUseCase getAllTrainingUseCase(TrainingGateway gateway) {
        return new GetAllTrainingUseCase(gateway);
    }

    @Bean
    public GetTrainingByIdUseCase getTrainingByIdUseCase(TrainingGateway gateway) {
        return new GetTrainingByIdUseCase(gateway);
    }

    @Bean
    public SaveTrainingUseCase saveTrainingUseCase(TrainingGateway gateway) {
        return new SaveTrainingUseCase(gateway);
    }

    @Bean
    public UpdateTrainingUseCase updateTrainingUseCase(TrainingGateway gateway) {
        return new UpdateTrainingUseCase(gateway);
    }

    @Bean
    public DeleteTrainingUseCase deleteTrainingUseCase(TrainingGateway gateway) {
        return new DeleteTrainingUseCase(gateway);
    }
}
