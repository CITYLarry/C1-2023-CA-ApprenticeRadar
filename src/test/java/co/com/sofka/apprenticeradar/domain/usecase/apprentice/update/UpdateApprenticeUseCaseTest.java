package co.com.sofka.apprenticeradar.domain.usecase.apprentice.update;

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

@ExtendWith(MockitoExtension.class)
class UpdateApprenticeUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        UpdateApprenticeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateApprenticeUseCase(gateway);
        }

        @Test
        @DisplayName("Update apprentice successfully")
        void apply() {
            var apprenticeId = "testId";
            var apprentice = new Apprentice(
                    "testId",
                    "test name",
                    "email@gmail.com"
            );

            Mockito.when(gateway.updateApprentice(apprenticeId, apprentice)).thenReturn(Mono.just(apprentice));

            var result = useCase.apply(apprenticeId, apprentice);

            StepVerifier.create(result)
                    .expectNext(apprentice)
                    .verifyComplete();

            Mockito.verify(gateway).updateApprentice(apprenticeId, apprentice);
        }

        @Test
        @DisplayName("Update apprentice with non-existent id")
        void updateGameNonExistentId() {

            var apprenticeId = "testId";
            var apprentice = new Apprentice(
                    "testId",
                    "test name",
                    "email@gmail.com"
            );

            Mockito.when(gateway.updateApprentice(apprenticeId, apprentice)).thenReturn(Mono.error(new RuntimeException("Could not find apprentice for id: " + apprenticeId)));

            var result = useCase.apply(apprenticeId, apprentice);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find apprentice for id: " + apprenticeId))
                    .verify();

            Mockito.verify(gateway).updateApprentice(apprenticeId, apprentice);
        }


    }