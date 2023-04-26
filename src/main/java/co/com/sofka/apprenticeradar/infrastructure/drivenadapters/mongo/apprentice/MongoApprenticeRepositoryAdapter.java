package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice.data.ApprenticeData;
import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training.data.TrainingData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoApprenticeRepositoryAdapter implements ApprenticeGateway {
    private final MongoApprenticeDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Apprentice> getAllApprentices() {
        return repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(apprenticeData -> mapper.map(apprenticeData, Apprentice.class));
    }

    @Override
    public Mono<Apprentice> getApprenticeById(String apprenticeId) {
        return repository
                .findById(apprenticeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find apprentice for id: " + apprenticeId)))
                .map(apprenticeData -> mapper.map(apprenticeData, Apprentice.class));
    }

    @Override
    public Mono<Apprentice> saveApprentice(Apprentice apprentice) {
        return repository
                .save(mapper.map(apprentice, ApprenticeData.class))
                .map(apprenticeData -> mapper.map(apprenticeData, Apprentice.class));
    }

    @Override
    public Mono<Apprentice> updateApprentice(String apprenticeId, Apprentice apprentice) {
        return repository
                .findById(apprenticeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find apprentice for id: " + apprenticeId)))
                .flatMap(apprenticeData -> {
                    apprenticeData.setApprenticeId(apprenticeData.getApprenticeId());
                    return repository.save(mapper.map(apprenticeData, ApprenticeData.class));
                })
                .map(apprenticeData -> mapper.map(apprenticeData, Apprentice.class));
    }

    @Override
    public Mono<Void> deleteApprentice(String apprenticeId) {
        return repository
                .findById(apprenticeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find apprentice for id: " + apprenticeId)))
                .flatMap(apprenticeData -> repository.deleteById(apprenticeData.getApprenticeId()));
    }

    @Override
    public Mono<Apprentice> getApprenticeByEmail(String email) {
        return repository
                .findApprenticeDataByEmail(email)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find apprentice for email: " + email)))
                .map(apprenticeData -> mapper.map(apprenticeData, Apprentice.class));
    }
}
