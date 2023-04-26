package co.com.sofka.apprenticeradar.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Apprentice {

    private String apprenticeId;
    private String name;
    private String email;

    private List<Descriptor> descriptorList = new ArrayList<>();
}
