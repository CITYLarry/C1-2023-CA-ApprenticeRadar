package co.com.sofka.apprenticeradar.domain.usecase.training.save;

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
class SaveTrainingUseCaseTest {

    @Mock
    TrainingGateway gateway;

    SaveTrainingUseCase useCase;

    @BeforeEach
    void setUp(){
        useCase = new SaveTrainingUseCase(gateway);
    }
    @Test
    @DisplayName("Save training successfully")
    void apply() {
        var training = new Training(
                "testId",
                "test name",
                "test cicle",
                new Radar(),
                new ArrayList<>()
        );

        Mockito.when(gateway.saveTraining(training)).thenReturn(Mono.just(training));

        var result = useCase.apply(training);

        StepVerifier.create(result)
                .expectNext(training)
                .verifyComplete();

        Mockito.verify(gateway).saveTraining(training);
    }
}