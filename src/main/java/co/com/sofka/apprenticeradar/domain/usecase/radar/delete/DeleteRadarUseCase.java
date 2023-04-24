package co.com.sofka.apprenticeradar.domain.usecase.radar.delete;

import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteRadarUseCase implements Function<String, Mono<Void>> {

    private final RadarGateway gateway;

    public Mono<Void> apply(String radarId){
        return gateway.deleteRadar(radarId);
    }
}
