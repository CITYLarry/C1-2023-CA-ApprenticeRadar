package co.com.sofka.apprenticeradar.domain.usecase.training.delete;

import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteTrainingUseCase implements Function<String, Mono<Void>> {

    private final TrainingGateway gateway;

    public Mono<Void> apply(String trainingId){
        return gateway.deleteTraining(trainingId);
    }
}
