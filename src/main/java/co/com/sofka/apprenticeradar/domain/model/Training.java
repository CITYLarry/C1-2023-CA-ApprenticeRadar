package co.com.sofka.apprenticeradar.domain.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Training {
    private String trainigId;
    private String trainingName;
    private String trainingCycle;
    private Radar trainingRadar;

}
