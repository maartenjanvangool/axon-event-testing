package io.orangebeard.events;

import lombok.Value;
import org.axonframework.serialization.Revision;

import java.time.LocalDateTime;

@Value
@Revision("2")
public class FlightDelayedEvent {
    Long flightId;
    LocalDateTime departureTime;
    Reason reason;
}
