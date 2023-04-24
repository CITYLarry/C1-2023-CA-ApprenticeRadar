package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.radar.data;

import co.com.sofka.apprenticeradar.domain.model.Descriptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "radars")
public class RadarData {

    @Id
    private String radarId;

    private List<Descriptor> descriptorList = new ArrayList<>();
}
