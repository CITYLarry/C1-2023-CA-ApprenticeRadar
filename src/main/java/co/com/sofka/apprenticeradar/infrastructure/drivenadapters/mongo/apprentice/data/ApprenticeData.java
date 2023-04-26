package co.com.sofka.apprenticeradar.infrastructure.drivenadapters.mongo.apprentice.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "apprentice")
public class ApprenticeData {
    @Id
    private String apprenticeId;
    private String name;
    private String email;
}
