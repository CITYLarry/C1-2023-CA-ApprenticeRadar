package co.com.sofka.apprenticeradar.domain.usecase.training.getall;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class GetAllTrainingUseCaseTest {

        @Mock
        TrainingGateway gateway;

        GetAllTrainingUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetAllTrainingUseCase(gateway);
        }

        @Test
        @DisplayName("Get all training successfully")
        void get() {

            var training1 = new Training(
                    "testId",
                    "test name",
                    "test cycle",
                    new Radar(),
                    new ArrayList<>()
            );

            var training2 = new Training(
                    "testId2",
                    "test name2",
                    "test cycle2",
                    new Radar(),
                    new ArrayList<>()
            );

            Mockito.when(gateway.getAllTrainings()).thenReturn(Flux.just(training1, training2));

            var result = useCase.get();

            StepVerifier.create(result)
                    .expectNext(training1)
                    .expectNext(training2)
                    .verifyComplete();

            Mockito.verify(gateway).getAllTrainings();
        }

        @Test
        @DisplayName("Get all training with empty response")
        void getEmptyResponse() {

            Mockito.when(gateway.getAllTrainings()).thenReturn(Flux.empty());

            var result = useCase.get();

            StepVerifier.create(result)
                    .verifyComplete();

            Mockito.verify(gateway).getAllTrainings();
        }
}