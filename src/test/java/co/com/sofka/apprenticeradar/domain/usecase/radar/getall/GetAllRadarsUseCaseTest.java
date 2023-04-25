package co.com.sofka.apprenticeradar.domain.usecase.radar.getall;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllRadarsUseCaseTest {

    @Mock
    RadarGateway gateway;

    GetAllRadarsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllRadarsUseCase(gateway);
    }

    @Test
    @DisplayName("Get all radars successfully")
    void get() {

        var r1 = new Radar("testId", List.of());
        var r2 = new Radar("testId2", List.of());


        Mockito.when(gateway.getAllRadars()).thenReturn(Flux.just(r1, r2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(r1)
                .expectNext(r2)
                .verifyComplete();

        Mockito.verify(gateway).getAllRadars();
    }

    @Test
    @DisplayName("Get all radars with empty response")
    void getEmptyResponse() {

        Mockito.when(gateway.getAllRadars()).thenReturn(Flux.empty());

        var result = useCase.get();

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).getAllRadars();
    }
}