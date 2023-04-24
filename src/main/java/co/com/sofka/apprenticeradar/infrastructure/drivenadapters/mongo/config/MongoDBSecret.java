package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MongoDBSecret {

    private final String uri;
}
