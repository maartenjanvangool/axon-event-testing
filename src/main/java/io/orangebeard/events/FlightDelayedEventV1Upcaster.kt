package io.orangebeard.events

import org.axonframework.serialization.SimpleSerializedType
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster
import org.dom4j.Document

internal class FlightDelayedEventV1Upcaster : SingleEventUpcaster() {

    public override fun canUpcast(intermediateRepresentation: IntermediateEventRepresentation): Boolean {
        return intermediateRepresentation.type.name == "io.orangebeard.events.FlightDelayedEvent"
                && intermediateRepresentation.type.revision == "1"
    }

    public override fun doUpcast(intermediateRepresentation: IntermediateEventRepresentation): IntermediateEventRepresentation {
        return intermediateRepresentation.upcastPayload(
                SimpleSerializedType(FlightDelayedEvent::class.java.typeName, "2"),
                Document::class.java
        ) { document ->
            if (document.rootElement.element("reason").text.equals("SECURITY_PROBLEM")) {
                document.rootElement.element("reason").text = "SECURITY_ISSUE"
            }
            document
        }
    }
}
