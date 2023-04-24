package co.com.sofka.apprenticeradar.domain.usecase.radar.getbyid;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetRadarByIdUseCase implements Function<String,Mono<Radar>> {

    private final RadarGateway gateway;

    public Mono<Radar> apply(String radarId) {
        return gateway.getRadarById(radarId);
    }
}
