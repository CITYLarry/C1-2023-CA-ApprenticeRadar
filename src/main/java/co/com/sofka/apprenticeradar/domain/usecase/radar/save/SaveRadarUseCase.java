package co.com.sofka.apprenticeradar.domain.usecase.radar.save;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveRadarUseCase implements Function<Radar, Mono<Radar>> {

    private final RadarGateway gateway;

    public Mono<Radar> apply(Radar radar) {
        return gateway.saveRadar(radar);
    }
}
