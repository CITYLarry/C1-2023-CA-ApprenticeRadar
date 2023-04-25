package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar.data.RadarData;
import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training.data.TrainingData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoTrainingRepositoryAdapter implements TrainingGateway {
    private final MongoTrainingDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Training> getAllTrainings() {
        return repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(trainingData -> mapper.map(trainingData, Training.class));
    }

    @Override
    public Mono<Training> getTrainingById(String trainId) {
        return repository
                .findById(trainId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find training for id: " + trainId)))
                .map(trainingData -> mapper.map(trainingData, Training.class));
    }

    @Override
    public Mono<Training> saveTraining(Training train) {
        return repository
                .save(mapper.map(train, TrainingData.class))
                .map(trainingData -> mapper.map(trainingData, Training.class));
    }

    @Override
    public Mono<Training> updateTraining(String trainId, Training train) {
        return repository
                .findById(trainId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find training for id: " + trainId)))
                .flatMap(radarData -> {
                    train.setTrainingId(radarData.getTrainingId());
                    return repository.save(mapper.map(train, TrainingData.class));
                })
                .map(trainingData -> mapper.map(trainingData, Training.class));
    }

    @Override
    public Mono<Void> deleteTraining(String trainId) {
        return repository
                .findById(trainId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find training for id: " + trainId)))
                .flatMap(trainigData -> repository.deleteById(trainigData.getTrainingId()));
    }
}
