package co.com.sofka.apprenticeradar.infrastructure.entrypoints.api.radar;

import co.com.sofka.apprenticeradar.domain.model.Apprentice;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.delete.DeleteApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getall.GetAllApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyemail.GetApprenticeByEmailUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.getbyid.GetApprenticeByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.save.SaveApprenticeUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.apprentice.update.UpdateApprenticeUseCase;
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
public class ApprenticeRouterRest {
    @Bean
    @RouterOperation(
            path = "/api/v1/apprentice",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllApprenticeUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllApprentices",
                    tags = "Apprentice use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Apprentice returned successfully",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Apprentice.class)))
                            ),
                            @ApiResponse(
                                    responseCode = "204",
                                    description = "No apprentice found",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllApprentices(GetAllApprenticeUseCase useCase) {
        return route(
                GET("/api/v1/apprentice"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Apprentice.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/apprentice/{apprenticeId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetApprenticeByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getApprenticeById",
                    tags = "Apprentice use cases",
                    parameters = {
                            @Parameter(
                                    name = "apprenticeId",
                                    description = "Apprentice ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Apprentice found successfully",
                                    content = @Content(schema = @Schema(implementation = Apprentice.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find apprentice for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getApprenticeById(GetApprenticeByIdUseCase useCase) {
        return route(
                GET("/api/v1/apprentice/{apprenticeId}"),
                request -> useCase.apply(request.pathVariable("apprenticeId"))
                        .flatMap(apprentice -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(apprentice))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/apprentice",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveApprenticeUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveApprentice",
                    tags = "Apprentice use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Apprentice object to be saved",
                            content = @Content(schema = @Schema(implementation = Apprentice.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Apprentice saved successfully",
                                    content = @Content(schema = @Schema(implementation = Apprentice.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> saveApprentice(SaveApprenticeUseCase useCase) {
        return route(
                POST("/api/v1/apprentice").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Apprentice.class)
                        .flatMap(apprentice -> useCase.apply(apprentice)
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
            path = "/api/v1/apprentice/{apprenticeId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateApprenticeUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateApprentice",
                    tags = "Apprentice use cases",
                    description = "Updates the Apprentice with the given ID",
                    parameters = {
                            @Parameter(
                                    name = "apprenticeId",
                                    description = "Apprentice ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            description = "Apprentice object to be updated",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Apprentice.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Apprentice updated successfully",
                                    content = @Content(schema = @Schema(implementation = Apprentice.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find apprentice for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> updateApprentice(UpdateApprenticeUseCase useCase) {
        return route(
                PUT("/api/v1/apprentice/{apprenticeId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Apprentice.class)
                        .flatMap(apprentice -> useCase.apply(request.pathVariable("apprenticeId"), apprentice)
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
            path = "/api/v1/apprentice/{apprenticeId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteApprenticeUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteApprentice",
                    tags = "Apprentice use cases",
                    parameters = {
                            @Parameter(
                                    name = "apprenticeId",
                                    description = "apprentice ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Apprentice deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find apprentice for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> deleteApprentice(DeleteApprenticeUseCase useCase) {
        return route(
                DELETE("/api/v1/apprentice/{apprenticeId}"),
                request -> useCase.apply(request.pathVariable("apprenticeId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted apprentice with id: " + request.pathVariable("apprenticeId")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/apprentice/email/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetApprenticeByEmailUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getApprenticeByEmail",
                    tags = "Apprentice use cases",
                    parameters = {
                            @Parameter(
                                    name = "email",
                                    description = "Apprentice email address",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Apprentice found successfully",
                                    content = @Content(schema = @Schema(implementation = Apprentice.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find apprentice for email:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getApprenticeByEmail(GetApprenticeByEmailUseCase useCase) {
        return route(
                GET("/api/v1/apprentice/email/{email}"),
                request -> useCase.apply(request.pathVariable("email"))
                        .flatMap(customer -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(customer))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

}
