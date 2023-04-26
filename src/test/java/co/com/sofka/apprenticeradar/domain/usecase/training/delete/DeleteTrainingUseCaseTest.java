package co.com.sofka.apprenticeradar.domain.usecase.training.delete;

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


@ExtendWith(MockitoExtension.class)
class DeleteTrainingUseCaseTest {

    @Mock
    TrainingGateway gateway;

    DeleteTrainingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteTrainingUseCase(gateway);
    }

    @Test
    @DisplayName("Delete training successfully")
    void apply() {
        var trainingId = "testId";

        Mockito.when(gateway.deleteTraining(trainingId)).thenReturn(Mono.empty());

        var result = useCase.apply(trainingId);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).deleteTraining(trainingId);
    }

    @Test
    @DisplayName("Delete training with non-existent id")
    void deleteNonExistentId() {
        var trainingId = "testId";

        Mockito.when(gateway.deleteTraining(trainingId)).thenReturn(Mono.error(new RuntimeException("Could not find training for id: " + trainingId)));

        var result = useCase.apply(trainingId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Could not find training for id: " + trainingId))
                .verify();

        Mockito.verify(gateway).deleteTraining(trainingId);
    }
}
