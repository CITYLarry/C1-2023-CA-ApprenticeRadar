package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyid;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetApprenticeByIdUseCase implements Function<String,Mono<Apprentice>> {

    private final ApprenticeGateway gateway;

    public Mono<Apprentice> apply(String apprenticeId) {
        return gateway.getApprenticeById(apprenticeId);
    }
}
