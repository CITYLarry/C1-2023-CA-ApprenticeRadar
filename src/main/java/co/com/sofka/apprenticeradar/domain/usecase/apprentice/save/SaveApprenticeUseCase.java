package co.com.sofka.apprenticeradar.domain.usecase.apprentice.save;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveApprenticeUseCase implements Function<Apprentice, Mono<Apprentice>> {

    private final ApprenticeGateway gateway;

    public Mono<Apprentice> apply(Apprentice apprentice) {
        return gateway.saveApprentice(apprentice);
    }
}
