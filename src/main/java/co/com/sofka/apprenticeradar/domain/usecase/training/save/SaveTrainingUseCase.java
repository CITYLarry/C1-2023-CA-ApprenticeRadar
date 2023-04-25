package co.com.sofka.apprenticeradar.domain.usecase.training.save;

import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveTrainingUseCase implements Function<Training, Mono<Training>> {

    private final TrainingGateway gateway;

    public Mono<Training> apply(Training training) {
        return gateway.saveTraining(training);
    }
}
