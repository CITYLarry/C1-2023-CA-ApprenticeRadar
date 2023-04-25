package co.com.sofka.apprenticeradar.domain.usecase.radar.getbyid;

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
class GetRadarByIdUseCaseTest {

    @Mock
    RadarGateway gateway;
    
    GetRadarByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetRadarByIdUseCase(gateway);
    }

    @Test
    @DisplayName("Get radar by id successfully")
    void apply() {

        var radarId = "testId";
        var radar = new Radar("testId", List.of());

        Mockito.when(gateway.getRadarById(radarId)).thenReturn(Mono.just(radar));

        var result = useCase.apply(radarId);

        StepVerifier.create(result)
                .expectNext(radar)
                .verifyComplete();

        Mockito.verify(gateway).getRadarById(radarId);
    }

    @Test
    @DisplayName("Get radar by id with non-existent id")
    void getByIdNonExistentId() {

        var radarId = "testId";

        var error = new RuntimeException("Could not find radar for id: " + radarId);

        Mockito.when(gateway.getRadarById(radarId)).thenReturn(Mono.error(error));

        var result = useCase.apply(radarId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals(error.getMessage()))
                .verify();

        Mockito.verify(gateway).getRadarById(radarId);
    }
}