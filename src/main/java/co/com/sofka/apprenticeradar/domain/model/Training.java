package co.com.sofka.apprenticeradar.domain.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Training {
    private String trainingId;
    private String trainingName;
    private String trainingCycle;
    private Radar trainingRadar;
    private List<Apprentice> apprenticesList = new ArrayList<>();

}
