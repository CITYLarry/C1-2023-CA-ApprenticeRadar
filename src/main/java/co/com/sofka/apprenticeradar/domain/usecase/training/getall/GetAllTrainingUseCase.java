package co.com.sofka.apprenticeradar.domain.usecase.training.getall;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllTrainingUseCase implements Supplier<Flux<Training>> {

    private final TrainingGateway gateway;

    public Flux<Training> get() {
        return gateway.getAllTrainings();
    }
}
