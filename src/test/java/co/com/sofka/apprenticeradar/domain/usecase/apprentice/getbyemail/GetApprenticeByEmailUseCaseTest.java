package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyemail;

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
class GetApprenticeByEmailUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        GetApprenticeByEmailUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetApprenticeByEmailUseCase(gateway);
        }

        @Test
        @DisplayName("Get apprentice by email successfully")
        void getByEmail() {

            var apprenticeEmail = "test@gmail.com";
            var apprentice = new Apprentice(
                    "testId",
                    "test name",
                    "test@gmail.com"
            );

            Mockito.when(gateway.getApprenticeByEmail(apprenticeEmail)).thenReturn(Mono.just(apprentice));

            var result = useCase.apply(apprenticeEmail);

            StepVerifier.create(result)
                    .expectNext(apprentice)
                    .verifyComplete();

            Mockito.verify(gateway).getApprenticeByEmail(apprenticeEmail);
        }

        @Test
        @DisplayName("Get apprentice by non-existent email")
        void getByIdNonExistentId() {

            var apprenticeEmail = "nonExistentEmail";

            Mockito.when(gateway.getApprenticeByEmail(apprenticeEmail)).thenReturn(Mono.error(new RuntimeException("Could not find apprentice for email: " + apprenticeEmail)));

            var result = useCase.apply(apprenticeEmail);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find apprentice for email: " + apprenticeEmail))
                    .verify();

            Mockito.verify(gateway).getApprenticeByEmail(apprenticeEmail);
        }
}