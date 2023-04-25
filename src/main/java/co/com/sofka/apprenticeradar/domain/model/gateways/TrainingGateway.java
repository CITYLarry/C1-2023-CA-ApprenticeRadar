package co.com.sofka.apprenticeradar.domain.model.gateways;

import co.com.sofka.apprenticeradar.domain.model.Training;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrainingGateway {
    Flux<Training> getAllTrainings();
    Mono<Training> getTrainingById(String trainId);
    Mono<Training> saveTraining(Training train);
    Mono<Training> updateTraining(String trainId, Training train);
    Mono<Void> deleteTraining(String trainId);
}
