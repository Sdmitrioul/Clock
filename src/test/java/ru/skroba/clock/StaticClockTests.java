package ru.skroba.clock;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticClockTests {
    @Test
    void testStaticClock() {
        final Instant max = Instant.MAX;
        
        final StaticClock clock = new StaticClock(max);
        
        assertEquals(max, clock.now());
        
        final Instant min = Instant.MIN;
        
        clock.setTime(min);
        
        assertEquals(min, clock.now());
    }
    
    @Test
    void exceptionThrows() {
        final StaticClock clock = new StaticClock(Instant.MAX);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> clock.setTime(null));
        
        assertTrue(exception.getMessage()
                .contains("Instant can't be null!"));
    }
}
