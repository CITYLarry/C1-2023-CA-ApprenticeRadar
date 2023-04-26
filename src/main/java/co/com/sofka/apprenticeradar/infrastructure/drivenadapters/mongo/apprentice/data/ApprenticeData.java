package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice.data;

import co.com.sofka.apprenticeradar.domain.model.Descriptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "apprentice")
public class ApprenticeData {
    @Id
    private String apprenticeId;
    private String name;
    private String email;

    private List<Descriptor> descriptorList = new ArrayList<>();
}
