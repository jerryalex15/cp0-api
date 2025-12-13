package com.example.cp0_api.mapper;

import com.example.cp0_api.dto.*;
import com.example.cp0_api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IAppMapper {

    DestinationResponse toDestinationResponse(Destination d);
    List<DestinationResponse> toDestinationResponseList(List<Destination> destinations);

    CircuitResponse toCircuitResponse(Circuit c);
    List<CircuitResponse> toCircuitResponseList(List<Circuit> circuits);

    ThematicResponseFull toThematicResponseFull(Thematic t);
    ThematicResponseLight toThematicResponseLight(Thematic t);
}