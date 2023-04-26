package co.com.sofka.apprenticeradar.domain.usecase.apprentice.delete;

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
class DeleteApprenticeUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        DeleteApprenticeUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteApprenticeUseCase(gateway);
        }

        @Test
        @DisplayName("Delete apprentice successfully")
        void apply() {
            var apprenticeId = "testId";

            Mockito.when(gateway.deleteApprentice(apprenticeId)).thenReturn(Mono.empty());

            var result = useCase.apply(apprenticeId);

            StepVerifier.create(result)
                    .verifyComplete();

            Mockito.verify(gateway).deleteApprentice(apprenticeId);
        }

        @Test
        @DisplayName("Delete apprentice with non-existent id")
        void deleteNonExistentId() {
            var apprenticeId = "testId";

            Mockito.when(gateway.deleteApprentice(apprenticeId)).thenReturn(Mono.error(new RuntimeException("Could not find apprentice for id: " + apprenticeId)));

            var result = useCase.apply(apprenticeId);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Could not find apprentice for id: " + apprenticeId))
                    .verify();

            Mockito.verify(gateway).deleteApprentice(apprenticeId);
        }

}