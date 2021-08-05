package io.orangebeard;

import io.orangebeard.commands.CreateFlightCommand;
import io.orangebeard.events.FlightCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FlightAggregateTest {

    private FixtureConfiguration<FlightAggregate> fixture;

    private static final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(FlightAggregate.class); // and pass serializer as argument?
    }

    @Test
    public void when_a_create_test_project_command_is_given_a_test_project_event_is_created() {
        fixture.givenNoPriorActivity()
                .when(new CreateFlightCommand(1L, now))
                .expectEvents(new FlightCreatedEvent(1L, now));
    }

    @Test
    public void the_aggregate_snapshot_serializes_correctly() {
        // fixture.givenNoPriorActivity()
        //        .when(new FlightCreatedEvent(1L, now))
        //        .expectSnapshotToMatch(xml);
    }

    @Test
    public void the_aggregate_snapshot_deserializes_correctly() {
        // fixture.givenSnapshot(snapshot)
        //        .when(new FlightCreatedEvent(1L, now))
        //        ...
    }



    private static final String snapshot = "" +
            "// xml of snapshot";
}