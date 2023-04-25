package co.com.sofka.apprenticeradar.domain.usecase.radar.delete;

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

@ExtendWith(MockitoExtension.class)
class DeleteRadarUseCaseTest {

    @Mock
    RadarGateway gateway;

    DeleteRadarUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteRadarUseCase(gateway);
    }

    @Test
    @DisplayName("Delete radar successfully")
    void apply() {
        var radarId = "testId";

        Mockito.when(gateway.deleteRadar(radarId)).thenReturn(Mono.empty());

        var result = useCase.apply(radarId);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).deleteRadar(radarId);
    }

    @Test
    @DisplayName("Delete radar with non-existent id")
    void deleteNonExistentId() {
        var radarId = "testId";

        Mockito.when(gateway.deleteRadar(radarId)).thenReturn(Mono.error(new RuntimeException("Could not find radar for id: " + radarId)));

        var result = useCase.apply(radarId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Could not find radar for id: " + radarId))
                .verify();

        Mockito.verify(gateway).deleteRadar(radarId);
    }
}