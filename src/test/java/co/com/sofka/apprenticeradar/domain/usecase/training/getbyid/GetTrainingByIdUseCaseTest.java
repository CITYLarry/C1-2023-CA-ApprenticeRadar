package co.com.sofka.apprenticeradar.domain.usecase.training.getbyid;

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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class GetTrainingByIdUseCaseTest {
        @Mock
        TrainingGateway gateway;

        GetTrainingByIdUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetTrainingByIdUseCase(gateway);
        }

        @Test
        @DisplayName("Get training by id successfully")
        void apply() {

            var trainingId = "testId";
            var training = new Training(
                    "testId",
                    "test name",
                    "test cicle",
                    new Radar(),
                    new ArrayList<>()
            );

            Mockito.when(gateway.getTrainingById(trainingId)).thenReturn(Mono.just(training));

            var result = useCase.apply(trainingId);

            StepVerifier.create(result)
                    .expectNext(training)
                    .verifyComplete();

            Mockito.verify(gateway).getTrainingById(trainingId);
        }

        @Test
        @DisplayName("Get training by id with non-existent id")
        void getByIdNonExistentId() {

            var trainingId = "testId";

            var error = new RuntimeException("Could not find training for id: " + trainingId);

            Mockito.when(gateway.getTrainingById(trainingId)).thenReturn(Mono.error(error));

            var result = useCase.apply(trainingId);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable.getMessage().equals(error.getMessage()))
                    .verify();

            Mockito.verify(gateway).getTrainingById(trainingId);
        }

}