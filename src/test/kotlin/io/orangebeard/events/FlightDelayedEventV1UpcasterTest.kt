package io.orangebeard.events

import com.thoughtworks.xstream.XStream
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.eventhandling.GenericDomainEventEntry
import org.axonframework.serialization.upcasting.event.InitialEventRepresentation
import org.axonframework.serialization.xml.XStreamSerializer
import org.dom4j.tree.AbstractDocument
import org.junit.jupiter.api.Test

internal class FlightDelayedEventV1UpcasterTest {

    // should be the serializer used in production
    private val serializer = XStreamSerializer.builder().xStream(XStream()).build()

    private val v1Upcaster = FlightDelayedEventV1Upcaster()

    @Test
    fun can_upcast() {
        val event = InitialEventRepresentation(
            GenericDomainEventEntry(
            "io.orangebeard.events.FlightDelayedEvent",
            "12045560",
            1L,
            "a904422b-6ae0-4123-a08a-e1e0cfd0f7bd",
            "2019-10-17T11:58:20.688Z",
                "io.orangebeard.events.FlightDelayedEvent",
            "1",
            xmlV1,
            ""), serializer)

        assertThat(v1Upcaster.canUpcast(event)).isTrue()

        val result = v1Upcaster.doUpcast(event)

        assertThat(result.data.type.revision).isEqualTo("2")
        assertXMLEquals(xmlV2, (result.data.data as AbstractDocument).asXML())
    }

    //language=xml
    private val xmlV1 = """
        <io.orangebeard.events.FlightCreatedEvent>
            <flightId>1</flightId>
            <departureTime>2019-10-08T12:42:23.329</departureTime>
            <reason>SECURITY_PROBLEM</reason>
        </io.orangebeard.events.FlightCreatedEvent>"""

    //language=xml
    private val xmlV2 = """
        <io.orangebeard.events.FlightCreatedEvent>
            <flightId>1</flightId>
            <departureTime>2019-10-08T12:42:23.329</departureTime>
            <reason>SECURITY_ISSUE</reason>
        </io.orangebeard.events.FlightCreatedEvent>"""
}