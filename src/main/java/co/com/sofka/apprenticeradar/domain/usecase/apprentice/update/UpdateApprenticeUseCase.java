package co.com.sofka.apprenticeradar.domain.usecase.apprentice.update;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateApprenticeUseCase implements BiFunction<String, Apprentice, Mono<Apprentice>> {

    private final ApprenticeGateway gateway;

    public Mono<Apprentice> apply(String apprenticeId, Apprentice apprentice) {
        return gateway.updateApprentice(apprenticeId, apprentice);
    }
}
