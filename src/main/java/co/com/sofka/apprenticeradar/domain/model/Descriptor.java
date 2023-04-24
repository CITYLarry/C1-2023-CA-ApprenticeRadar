package co.com.sofka.apprenticeradar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Descriptor {

    private String knowledgeArea;
    private String description;

    private Double factual;
    private Double conceptual;
    private Double procedural;
    private Double metacognitive;
    private Double approvalLevel;
}
