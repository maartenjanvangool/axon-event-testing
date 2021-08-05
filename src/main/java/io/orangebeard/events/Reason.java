package io.orangebeard.events;

public enum Reason {
    @Deprecated // upcast to event version v2, use SECURITY_ISSUE
    SECURITY_PROBLEM,
    SECURITY_ISSUE,
    BAGGAGE_MISSING,
    PASSENGER_MISSING,
}
