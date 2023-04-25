package co.com.sofka.apprenticeradar.domain.usecase.training.update;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateTrainingUseCase implements BiFunction<String, Training, Mono<Training>> {

    private final TrainingGateway gateway;

    public Mono<Training> apply(String trainingId, Training training) {
        return gateway.updateTraining(trainingId, training);
    }
}
