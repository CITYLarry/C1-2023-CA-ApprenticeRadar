package co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyid;

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
class GetApprenticeByIdUseCaseTest {
        @Mock
        ApprenticeGateway gateway;

        GetApprenticeByIdUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetApprenticeByIdUseCase(gateway);
        }

        @Test
        @DisplayName("Get apprentice by id successfully")
        void apply() {

            var apprenticeId = "testId";
            var apprentice = new Apprentice(
                    "testId",
                    "test name",
                    "email@gmail.com"
            );

            Mockito.when(gateway.getApprenticeById(apprenticeId)).thenReturn(Mono.just(apprentice));

            var result = useCase.apply(apprenticeId);

            StepVerifier.create(result)
                    .expectNext(apprentice)
                    .verifyComplete();

            Mockito.verify(gateway).getApprenticeById(apprenticeId);
        }

        @Test
        @DisplayName("Get apprentice by id with non-existent id")
        void getByIdNonExistentId() {

            var apprenticeId = "testId";

            var error = new RuntimeException("Could not find apprentice for id: " + apprenticeId);

            Mockito.when(gateway.getApprenticeById(apprenticeId)).thenReturn(Mono.error(error));

            var result = useCase.apply(apprenticeId);

            StepVerifier.create(result)
                    .expectErrorMatches(throwable -> throwable.getMessage().equals(error.getMessage()))
                    .verify();

            Mockito.verify(gateway).getApprenticeById(apprenticeId);
        }
}