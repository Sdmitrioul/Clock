package ru.skroba.event;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skroba.Util.getEventString;

public class NamedEventTests {
    @Test
    void testPutAndGetRPC() {
        final Event event = getEventWithData();
        
        assertEquals(1.0, event.getRPM(Instant.parse("2007-12-03T10:15:30.01Z"), 1, TimeUnit.MINUTES));
        assertEquals(1.0, event.getRPM(Instant.parse("2007-12-03T10:21:30.01Z"), 7, TimeUnit.MINUTES));
        
        assertEquals(3.0 / 180, event.getRPM(Instant.parse("2007-12-03T13:21:30.01Z"), 3, TimeUnit.HOURS));
    }
    
    private static Event getEventWithData() {
        final Event event = new NamedEvent("Test");
        
        event.put(Instant.parse("2007-12-03T10:15:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:16:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:17:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:18:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:19:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:20:30.00Z"));
        event.put(Instant.parse("2007-12-03T10:21:30.00Z"));
        
        event.put(Instant.parse("2007-12-03T11:21:30.00Z"));
        event.put(Instant.parse("2007-12-03T12:21:30.00Z"));
        event.put(Instant.parse("2007-12-03T13:21:30.00Z"));
        
        return event;
    }
    
    @Test
    void testToString() {
        assertEquals(getEventString("Test", 1.0),
                getEventWithData().toString(Instant.parse("2007-12-03T10:15:30.01Z"), 1, TimeUnit.MINUTES));
    }
}
