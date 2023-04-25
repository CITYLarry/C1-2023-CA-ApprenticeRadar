package co.com.sofka.apprenticeradar.domain.usecase.training.getbyid;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetTrainingByIdUseCase implements Function<String,Mono<Training>> {

    private final TrainingGateway gateway;

    public Mono<Training> apply(String trainingId) {
        return gateway.getTrainingById(trainingId);
    }
}
