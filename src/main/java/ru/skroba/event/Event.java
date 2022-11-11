package ru.skroba.event;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public interface Event {
    void put(Instant instant);
    
    double getRPM(Instant now, long time, TimeUnit unit);
    
    String toString(Instant now, long time, TimeUnit unit);
}
