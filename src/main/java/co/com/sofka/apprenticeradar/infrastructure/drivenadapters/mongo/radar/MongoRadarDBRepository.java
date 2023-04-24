package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar;

import co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar.data.RadarData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoRadarDBRepository extends ReactiveMongoRepository<RadarData, String> {
}
