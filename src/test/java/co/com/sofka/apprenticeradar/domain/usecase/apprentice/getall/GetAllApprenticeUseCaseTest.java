package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getall;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;



@ExtendWith(MockitoExtension.class)
class GetAllApprenticeUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        GetAllApprenticeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetAllApprenticeUseCase(gateway);
        }

        @Test
        @DisplayName("Get all apprentice successfully")
        void get() {

            var apprentice1 = new Apprentice(
                    "testId",
                    "test name",
                    "email@gmail.com"
            );

            var apprentice2 = new Apprentice(
                    "testId2",
                    "test name",
                    "email@gmail.com"
            );

            Mockito.when(gateway.getAllApprentices()).thenReturn(Flux.just(apprentice1, apprentice2));

            var result = useCase.get();

            StepVerifier.create(result)
                    .expectNext(apprentice1)
                    .expectNext(apprentice2)
                    .verifyComplete();

            Mockito.verify(gateway).getAllApprentices();
        }

        @Test
        @DisplayName("Get all apprentice with empty response")
        void getEmptyResponse() {

            Mockito.when(gateway.getAllApprentices()).thenReturn(Flux.empty());

            var result = useCase.get();

            StepVerifier.create(result)
                    .verifyComplete();

            Mockito.verify(gateway).getAllApprentices();
        }

}