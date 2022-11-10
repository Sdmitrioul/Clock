package ru.skroba.clock;

import java.time.Instant;

public final class RealClock implements Clock {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
