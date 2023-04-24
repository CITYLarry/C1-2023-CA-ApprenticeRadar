package co.com.sofka.apprenticeradar.infrastructure.entrypoints.api.radar;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.usecase.radar.getall.GetAllRadarsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RadarRouterRest {

    @Bean
    @RouterOperation(
            path = "/api/v1/radars",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllRadarsUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllRadars",
                    tags = "Radar use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Radars returned successfully",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Radar.class)))
                            ),
                            @ApiResponse(
                                    responseCode = "204",
                                    description = "No radars found",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllRadars(GetAllRadarsUseCase useCase) {
        return route(
                GET("/api/v1/radars"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Radar.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }
}
