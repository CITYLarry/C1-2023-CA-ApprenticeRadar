package co.com.sofka.apprenticeradar.domain.model.gateways;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RadarGateway {

    Flux<Radar> getAllRadars();
    Mono<Radar> getRadarById(String radarId);
    Mono<Radar> saveRadar(Radar radar);
    Mono<Radar> updateRadar(String radarId, Radar radar);
    Mono<Void> deleteRadar(String radarId);
}
