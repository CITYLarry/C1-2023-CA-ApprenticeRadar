package co.com.sofka.apprenticeradar.domain.usecase.radar.delete;

import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DeleteRadarUseCase implements Function<String, Mono<Void>> {

    private RadarGateway gateway;

    public Mono<Void> apply(String radarId){
        return gateway.deleteRadar(radarId);
    }
}
