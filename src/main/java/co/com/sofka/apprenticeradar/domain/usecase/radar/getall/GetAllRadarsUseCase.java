package co.com.sofka.apprenticeradar.domain.usecase.radar.getall;

import co.com.sofka.apprenticeradar.domain.model.Radar;
import co.com.sofka.apprenticeradar.domain.model.gateways.RadarGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllRadarsUseCase implements Supplier<Flux<Radar>> {

    private final RadarGateway gateway;

    public Flux<Radar> get() {
        return gateway.getAllRadars();
    }
}
