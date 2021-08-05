package io.orangebeard;

import com.thoughtworks.xstream.XStream;
import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.snapshotting.RevisionSnapshotFilter;
import org.axonframework.eventsourcing.snapshotting.SnapshotFilter;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    XStream xstream() {
        return new XStream();
    }

    @Bean
    @Primary
    public Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }

    @Bean
    public AxonServerEventStore eventStorageEngine(AxonServerConfiguration configuration,
                                                   AxonServerConnectionManager axonServerConnectionManager,
                                                   Serializer serializer,
                                                   SnapshotFilter snapshotFilter) {
        return AxonServerEventStore
                .builder()
                .eventSerializer(serializer)
                .snapshotFilter(snapshotFilter)
                .configuration(configuration)
                .platformConnectionManager(axonServerConnectionManager)
                //.upcasterChain(getUpcasterStream())
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition aggregateSnapshotDefinition(SpringAggregateSnapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 1000);
    }

    @Bean
    public SnapshotFilter aggregateSnapshotFilter() {
        return RevisionSnapshotFilter.builder()
                .revision(FlightAggregate.REVISION)
                .type("FlightAggregate")
                .build();
    }
}
