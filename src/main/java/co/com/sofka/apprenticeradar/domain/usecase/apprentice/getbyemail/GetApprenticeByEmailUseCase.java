package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyemail;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetApprenticeByEmailUseCase implements Function<String, Mono<Apprentice>> {

    private final ApprenticeGateway gateway;
    @Override
    public Mono<Apprentice> apply(String email) {
        return gateway.getApprenticeByEmail(email);
    }
}
