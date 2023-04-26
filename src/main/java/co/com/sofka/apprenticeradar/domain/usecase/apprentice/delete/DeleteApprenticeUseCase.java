package co.com.sofka.apprenticeradar.domain.usecase.apprentice.delete;

import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteApprenticeUseCase implements Function<String, Mono<Void>> {

    private final ApprenticeGateway gateway;

    public Mono<Void> apply(String apprenticeId){
        return gateway.deleteApprentice(apprenticeId);
    }
}
