package co.com.sofka.apprenticeradar.domain.usecase.apprentice.save;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.model.gateways.ApprenticeGateway;
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
class SaveApprenticeUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        SaveApprenticeUseCase useCase;

        @BeforeEach
        void setUp(){
            useCase = new SaveApprenticeUseCase(gateway);
        }
        @Test
        @DisplayName("Save apprentice successfully")
        void apply() {
            var apprentice = new Apprentice(
                    "testId",
                    "test name",
                    "email@gmail.com"
            );

            Mockito.when(gateway.saveApprentice(apprentice)).thenReturn(Mono.just(apprentice));

            var result = useCase.apply(apprentice);

            StepVerifier.create(result)
                    .expectNext(apprentice)
                    .verifyComplete();

            Mockito.verify(gateway).saveApprentice(apprentice);
        }
}