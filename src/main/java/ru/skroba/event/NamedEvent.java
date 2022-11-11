package ru.skroba.event;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public final class NamedEvent implements Event {
    private final String taskName;
    private final List<Instant> events = new ArrayList<>();
    
    public NamedEvent(final String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public void put(final Instant instant) {
        events.add(instant);
    }
    
    @Override
    public double getRPM(final Instant now, final long time, final TimeUnit unit) {
        final Instant fromTime = now.minus(time, unit.toChronoUnit());
        
        final int countOfMinutes = (int) Duration.between(fromTime, now)
                .abs()
                .toMinutes();
        
        return ((double) getFiltered(now, fromTime).size()) / countOfMinutes;
    }
    
    @Override
    public String toString(final Instant now, final long time, final TimeUnit unit) {
        return "[Name: " + taskName + "; rpm: " + String.format("%.2f]", getRPM(now, time, unit));
    }
    
    private List<Instant> getFiltered(final Instant to, final Instant fromTime) {
        return this.events.stream()
                .filter(it -> it.isAfter(fromTime) && it.isBefore(to))
                .collect(Collectors.toList());
    }
}
