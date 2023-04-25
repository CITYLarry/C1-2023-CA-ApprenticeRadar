package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training;

import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training.data.TrainingData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoTrainingDBRepository extends ReactiveMongoRepository<TrainingData, String> {
}
