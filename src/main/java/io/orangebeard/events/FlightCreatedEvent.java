package io.orangebeard.events;

import lombok.Value;
import org.axonframework.serialization.Revision;

import java.time.LocalDateTime;

@Value
@Revision("1")
public class FlightCreatedEvent {
    Long flightId;
    LocalDateTime departureTime;

}
