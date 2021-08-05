package io.orangebeard.commands;

import io.orangebeard.events.Reason;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class DelayFlightCommand {
    Long flightId;
    LocalDateTime departureTime;
    Reason reason;

}
