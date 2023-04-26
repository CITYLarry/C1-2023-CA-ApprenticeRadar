package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.training.data;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.Radar;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "trainings")
public class TrainingData {

    @Id
    private String trainingId;
    private String trainingName;
    private String trainingCycle;
    private Radar trainingRadar;
    private List<Apprentice> apprenticesList = new ArrayList<>();
}
