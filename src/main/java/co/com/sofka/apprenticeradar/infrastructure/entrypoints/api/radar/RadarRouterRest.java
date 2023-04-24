package co.com.sofka.apprenticeradar.infrastructure.entrypoints.api.radar;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.usecase.radar.delete.DeleteRadarUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.getall.GetAllRadarsUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.getbyid.GetRadarByIdUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.save.SaveRadarUseCase;
import co.com.sofka.apprenticeradar.domain.usecase.radar.update.UpdateRadarUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
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

    @Bean
    @RouterOperation(
            path = "/api/v1/radars/{radarId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetRadarByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getRadarById",
                    tags = "Radar use cases",
                    parameters = {
                            @Parameter(
                                    name = "radarId",
                                    description = "Radar ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Radar found successfully",
                                    content = @Content(schema = @Schema(implementation = Radar.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find radar for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getRadarById(GetRadarByIdUseCase useCase) {
        return route(
                GET("/api/v1/radars/{radarId}"),
                request -> useCase.apply(request.pathVariable("radarId"))
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
            path = "/api/v1/radars",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveRadarUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveRadar",
                    tags = "Radar use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Radar object to be saved",
                            content = @Content(schema = @Schema(implementation = Radar.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Radar saved successfully",
                                    content = @Content(schema = @Schema(implementation = Radar.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> saveRadar(SaveRadarUseCase useCase) {
        return route(
                POST("/api/v1/radars").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Radar.class)
                        .flatMap(radar -> useCase.apply(radar)
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
            path = "/api/v1/radars/{radarId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateRadarUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateRadar",
                    tags = "Radar use cases",
                    description = "Updates the radar with the given ID",
                    parameters = {
                            @Parameter(
                                    name = "radarId",
                                    description = "Radar ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            description = "Radar object to be updated",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Radar.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Radar updated successfully",
                                    content = @Content(schema = @Schema(implementation = Radar.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find radar for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> updateRadar(UpdateRadarUseCase useCase) {
        return route(
                PUT("/api/v1/radars/{radarId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Radar.class)
                        .flatMap(radar -> useCase.apply(request.pathVariable("radarId"), radar)
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
            path = "/api/v1/radars/{radarId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteRadarUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteRadar",
                    tags = "Radar use cases",
                    parameters = {
                            @Parameter(
                                    name = "radarId",
                                    description = "Radar ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Radar deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find radar for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> deleteRadar(DeleteRadarUseCase useCase) {
        return route(
                DELETE("/api/v1/radars/{radarId}"),
                request -> useCase.apply(request.pathVariable("radarId"))
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
