package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice;

import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice.data.ApprenticeData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoApprenticeDBRepository extends ReactiveMongoRepository<ApprenticeData, String> {
    Mono<ApprenticeData> findApprenticeDataByEmail(String email);
}
