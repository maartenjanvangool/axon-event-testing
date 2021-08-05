package io.orangebeard.commands;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreateFlightCommand {
    Long flightId;
    LocalDateTime departureTime;
}
