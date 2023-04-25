package co.com.sofka.apprenticeradar.domain.model.gateways;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApprenticeGateway {
    Flux<Apprentice> getAllApprentices();
    Mono<Apprentice> getApprenticeById(String apprenticeId);
    Mono<Apprentice> saveApprentice(Apprentice apprentice);
    Mono<Apprentice> updateApprentice(String apprenticeId, Apprentice apprentice);
    Mono<Void> deleteApprentice(String apprenticeId);
}
