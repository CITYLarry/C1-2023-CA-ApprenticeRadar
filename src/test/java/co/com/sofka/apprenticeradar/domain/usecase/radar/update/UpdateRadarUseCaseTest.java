package co.com.sofka.apprenticeradar.domain.usecase.radar.update;

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
class UpdateRadarUseCaseTest {

    @Mock
    RadarGateway gateway;

    UpdateRadarUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateRadarUseCase(gateway);
    }

    @Test
    @DisplayName("Update radar successfully")
    void apply() {
        var radarId = "testId";
        var radar = new Radar("testId", List.of());

        Mockito.when(gateway.updateRadar(radarId, radar)).thenReturn(Mono.just(radar));

        var result = useCase.apply(radarId, radar);

        StepVerifier.create(result)
                .expectNext(radar)
                .verifyComplete();

        Mockito.verify(gateway).updateRadar(radarId, radar);
    }

    @Test
    @DisplayName("Update radar with non-existent id")
    void updateGameNonExistentId() {

        var radarId = "testId";
        var radar = new Radar("testId", List.of());

        Mockito.when(gateway.updateRadar(radarId, radar)).thenReturn(Mono.error(new RuntimeException("Could not find radar for id: " + radarId)));

        var result = useCase.apply(radarId, radar);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find radar for id: " + radarId))
                .verify();

        Mockito.verify(gateway).updateRadar(radarId, radar);
    }
}