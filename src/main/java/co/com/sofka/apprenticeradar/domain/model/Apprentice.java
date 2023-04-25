package co.com.sofka.apprenticeradar.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Apprentice {

    private String apprenticeId;
    private String name;
    private String email;

}
