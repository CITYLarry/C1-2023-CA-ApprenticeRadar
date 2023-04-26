package co.com.sofka.apprenticeradar.domain.usecase.training.update;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import co.com.sofka.apprenticeradar.domain.model.gateways.TrainingGateway;
import co.com.sofka.apprenticeradar.domain.usecase.radar.update.UpdateRadarUseCase;
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
import java.util.List;


@ExtendWith(MockitoExtension.class)
class UpdateTrainingUseCaseTest {

        @Mock
        TrainingGateway gateway;

        UpdateTrainingUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateTrainingUseCase(gateway);
        }

        @Test
        @DisplayName("Update training successfully")
        void apply() {
            var trainingId = "testId";
            var training = new Training(
                    "testId",
                    "test name",
                    "test cicle",
                    new Radar(),
                    new ArrayList<>()
            );

            Mockito.when(gateway.updateTraining(trainingId, training)).thenReturn(Mono.just(training));

            var result = useCase.apply(trainingId, training);

            StepVerifier.create(result)
                    .expectNext(training)
                    .verifyComplete();

            Mockito.verify(gateway).updateTraining(trainingId, training);
        }

        @Test
        @DisplayName("Update training with non-existent id")
        void updateGameNonExistentId() {

            var trainingId = "testId";
            var training = new Training(
                    "testId",
                    "test name",
                    "test cicle",
                    new Radar(),
                    new ArrayList<>()
            );

            Mockito.when(gateway.updateTraining(trainingId, training)).thenReturn(Mono.error(new RuntimeException("Could not find training for id: " + trainingId)));

            var result = useCase.apply(trainingId, training);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find training for id: " + trainingId))
                    .verify();

            Mockito.verify(gateway).updateTraining(trainingId, training);
        }

}