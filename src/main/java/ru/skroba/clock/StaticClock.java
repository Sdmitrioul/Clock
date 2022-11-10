package ru.skroba.clock;

import java.time.Instant;

public final class StaticClock implements Clock {
    private final Instant time;
    
    public StaticClock(final Instant time) {
        this.time = time;
    }
    
    @Override
    public Instant now() {
        return time;
    }
}
