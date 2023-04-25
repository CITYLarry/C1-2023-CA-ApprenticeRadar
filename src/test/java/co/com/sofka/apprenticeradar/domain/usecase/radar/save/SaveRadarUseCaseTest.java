package co.com.sofka.apprenticeradar.domain.usecase.radar.save;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SaveRadarUseCaseTest {

    @Mock
    RadarGateway gateway;

    SaveRadarUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SaveRadarUseCase(gateway);
    }

    @Test
    @DisplayName("Save radar successfully")
    void apply() {
        var radar = new Radar("testId", List.of());

        Mockito.when(gateway.saveRadar(radar)).thenReturn(Mono.just(radar));

        var result = useCase.apply(radar);

        StepVerifier.create(result)
                .expectNext(radar)
                .verifyComplete();

        Mockito.verify(gateway).saveRadar(radar);
    }
}