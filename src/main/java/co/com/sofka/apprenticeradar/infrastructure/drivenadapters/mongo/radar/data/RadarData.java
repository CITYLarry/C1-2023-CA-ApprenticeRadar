package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.stream.Stream;

@Data
@NoArgsConstructor
@Document(collection = "radars")
public class RadarData {

    @Id
    private String radarId;
    private String knowledgeArea;
    private String description;

    private Double factual;
    private Double conceptual;
    private Double procedural;
    private Double metacognitive;
    private Double approvalLevel;
}
