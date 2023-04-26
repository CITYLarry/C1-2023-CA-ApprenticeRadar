package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getall;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllApprenticeUseCase implements Supplier<Flux<Apprentice>> {

    private final ApprenticeGateway gateway;

    public Flux<Apprentice> get() {
        return gateway.getAllApprentices();
    }
}
