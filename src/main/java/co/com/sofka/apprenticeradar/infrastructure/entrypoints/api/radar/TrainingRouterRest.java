package co.com.sofka.apprenticeradar.infrastructure.entrypoints.api.radar;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.Training;
import co.com.sofka.apprenticeradar.domain.usecase.training.delete.DeleteTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getall.GetAllTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.getbyid.GetTrainingByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.save.SaveTrainingUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.training.update.UpdateTrainingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TrainingRouterRest {
    @Bean
    @RouterOperation(
            path = "/api/v1/training",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllTrainingUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllTrainings",
                    tags = "Training use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Training returned successfully",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Radar.class)))
                            ),
                            @ApiResponse(
                                    responseCode = "204",
                                    description = "No training found",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllTrainings(GetAllTrainingUseCase useCase) {
        return route(
                GET("/api/v1/training"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Training.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/training/{trainingId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetTrainingByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getTrainingById",
                    tags = "Training use cases",
                    parameters = {
                            @Parameter(
                                    name = "trainingId",
                                    description = "Training ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Training found successfully",
                                    content = @Content(schema = @Schema(implementation = Radar.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find training for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getTrainingById(GetTrainingByIdUseCase useCase) {
        return route(
                GET("/api/v1/training/{trainingId}"),
                request -> useCase.apply(request.pathVariable("trainingId"))
                        .flatMap(radar -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(radar))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/training",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveTrainingUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveTraining",
                    tags = "Training use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Training object to be saved",
                            content = @Content(schema = @Schema(implementation = Training.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Training saved successfully",
                                    content = @Content(schema = @Schema(implementation = Training.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> saveTraining(SaveTrainingUseCase useCase) {
        return route(
                POST("/api/v1/training").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Training.class)
                        .flatMap(training -> useCase.apply(training)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/training/{trainingId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateTrainingUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateTraining",
                    tags = "Training use cases",
                    description = "Updates the Training with the given ID",
                    parameters = {
                            @Parameter(
                                    name = "trainingId",
                                    description = "Training ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            description = "Training object to be updated",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Training.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Training updated successfully",
                                    content = @Content(schema = @Schema(implementation = Training.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find training for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> updateTraining(UpdateTrainingUseCase useCase) {
        return route(
                PUT("/api/v1/training/{trainingId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Training.class)
                        .flatMap(training -> useCase.apply(request.pathVariable("trainingId"), training)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/training/{trainingId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteTrainingUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteTraining",
                    tags = "Training use cases",
                    parameters = {
                            @Parameter(
                                    name = "trainingId",
                                    description = "training ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Training deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find training for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> deleteTraining(DeleteTrainingUseCase useCase) {
        return route(
                DELETE("/api/v1/training/{trainingId}"),
                request -> useCase.apply(request.pathVariable("trainingId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted radar with id: " + request.pathVariable("radarId")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
