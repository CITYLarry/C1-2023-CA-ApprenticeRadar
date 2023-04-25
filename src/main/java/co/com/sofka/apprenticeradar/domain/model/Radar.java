package co.com.sofka.apprenticeradar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Radar {

    private String radarId;
    private String knowledgeArea;
    private String description;

    private Double factual;
    private Double conceptual;
    private Double procedural;
    private Double metacognitive;
    private Double approvalLevel;
}
