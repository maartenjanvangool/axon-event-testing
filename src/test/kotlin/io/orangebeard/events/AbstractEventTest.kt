package io.orangebeard.events

import org.assertj.core.api.Assertions
import org.custommonkey.xmlunit.DetailedDiff
import org.custommonkey.xmlunit.XMLUnit

fun assertXMLEquals(expectedXML: String, actualXML: String) {
    XMLUnit.setIgnoreWhitespace(true)
    XMLUnit.setIgnoreAttributeOrder(true)
    XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true)

    val diff = DetailedDiff(XMLUnit.compareXML(expectedXML, actualXML))

    val allDifferences = diff.allDifferences
    Assertions.assertThat(allDifferences).hasSize(0)
}
