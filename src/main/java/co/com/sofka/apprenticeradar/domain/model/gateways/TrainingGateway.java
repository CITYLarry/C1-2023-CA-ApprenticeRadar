package co.com.sofka.apprenticeradar.domain.model.gateways;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrainingGateway {
    Flux<Radar> getAllTrainings();
    Mono<Radar> getTrainingById(String radarId);
    Mono<Radar> saveTraining(Radar radar);
    Mono<Radar> updateTraining(String radarId, Radar radar);
    Mono<Void> deleteTraining(String radarId);
}
