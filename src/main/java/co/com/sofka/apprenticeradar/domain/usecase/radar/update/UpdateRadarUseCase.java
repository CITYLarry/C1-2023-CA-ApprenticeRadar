package co.com.sofka.apprenticeradar.domain.usecase.radar.update;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateRadarUseCase implements BiFunction<String, Radar, Mono<Radar>> {

    private final RadarGateway gateway;

    public Mono<Radar> apply(String radarId, Radar radar) {
        return gateway.updateRadar(radarId, radar);
    }
}
