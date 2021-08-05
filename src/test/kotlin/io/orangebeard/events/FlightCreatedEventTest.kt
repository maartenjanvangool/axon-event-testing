package io.orangebeard.events

import com.thoughtworks.xstream.XStream
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.serialization.xml.XStreamSerializer
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class FlightCreatedEventTest {

    private val serializer = XStreamSerializer.builder().xStream(XStream()).build()

    @Test
    fun can_serialize_and_deserialize() {
        val serializedObject = serializer.serialize(event, String::class.java)
        val result = serializer.deserialize<String, FlightCreatedEvent>(serializedObject)

        assertXMLEquals(serializedObject.data, xml)
        assertThat(event).isEqualTo(result)
    }

    private val event: FlightCreatedEvent = FlightCreatedEvent(
        1L,
        LocalDateTime.parse("2019-10-08T12:42:23.329")
    )

    //language=xml
    private val xml = """
        <io.orangebeard.events.FlightCreatedEvent>
            <flightId>1</flightId>
            <departureTime>2019-10-08T12:42:23.329</departureTime>
        </io.orangebeard.events.FlightCreatedEvent>"""
}
