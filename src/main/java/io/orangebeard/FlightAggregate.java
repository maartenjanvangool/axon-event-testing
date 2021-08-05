package io.orangebeard;

import io.orangebeard.commands.CreateFlightCommand;
import io.orangebeard.commands.DelayFlightCommand;
import io.orangebeard.events.FlightCreatedEvent;
import io.orangebeard.events.FlightDelayedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition = "aggregateSnapshotDefinition")
@Revision(FlightAggregate.REVISION)
@SuppressWarnings("unused")
public class FlightAggregate {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightAggregate.class);
    public static final String REVISION = "1";

    @AggregateIdentifier
    private Long flightId;

    private LocalDateTime departureTime;

    public FlightAggregate() {
    }

    @CommandHandler
    public FlightAggregate(CreateFlightCommand command) {
        apply(new FlightCreatedEvent(command.getFlightId(), command.getDepartureTime()));
    }

    @CommandHandler
    public void handle(DelayFlightCommand command) {
        if (command.getDepartureTime().isAfter(departureTime)) {
            apply(new FlightDelayedEvent(command.getFlightId(), command.getDepartureTime(), command.getReason()));
        }
    }

    @EventSourcingHandler
    public void on(FlightCreatedEvent event) {
        flightId = event.getFlightId();
        departureTime = event.getDepartureTime();
    }

    @EventSourcingHandler
    public void on(FlightDelayedEvent event) {
        this.departureTime = event.getDepartureTime();
    }

    @Override
    public String toString() {
        return flightId.toString();
    }
}
