package ru.skroba.clock;

import java.time.Instant;

public final class StaticClock implements Clock {
    private Instant time;
    
    public StaticClock(final Instant time) {
        this.time = time;
    }
    
    public void setTime(final Instant time) {
        if (time == null) {
            throw new IllegalArgumentException("Instant can't be null!");
        }
        this.time = time;
    }
    
    @Override
    public Instant now() {
        return time;
    }
}
