package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar.data.RadarData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRadarRepositoryAdapter implements RadarGateway {

    private final MongoRadarDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Radar> getAllRadars() {
        return repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(radarData -> mapper.map(radarData, Radar.class));
    }

    @Override
    public Mono<Radar> getRadarById(String radarId) {
        return repository
                .findById(radarId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find radar for id: " + radarId)))
                .map(radarData -> mapper.map(radarData, Radar.class));
    }

    @Override
    public Mono<Radar> saveRadar(Radar radar) {
        return repository
                .save(mapper.map(radar, RadarData.class))
                .map(radarData -> mapper.map(radarData, Radar.class));
    }

    @Override
    public Mono<Radar> updateRadar(String radarId, Radar radar) {
        return repository
                .findById(radarId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find radar for id: " + radarId)))
                .flatMap(radarData -> {
                    radar.setRadarId(radarData.getRadarId());
                    return repository.save(mapper.map(radar, RadarData.class));
                })
                .map(radarData -> mapper.map(radarData, Radar.class));
    }

    @Override
    public Mono<Void> deleteRadar(String radarId) {
        return repository
                .findById(radarId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find radar for id: " + radarId)))
                .flatMap(radarData -> repository.deleteById(radarData.getRadarId()));
    }
}
