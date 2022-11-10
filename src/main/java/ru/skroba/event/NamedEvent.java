package ru.skroba.event;

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
    public boolean put(final Instant instant) {
        return events.add(instant);
    }
    
    @Override
    public List<Instant> getTimes() {
        return List.copyOf(events);
    }
    
    @Override
    public List<Instant> getFiltered(final int time, final TimeUnit unit) {
        final Instant borderTime = Instant.now()
                .minus(time, unit.toChronoUnit());
        return events.stream()
                .filter(it -> it.compareTo(borderTime) > 0)
                .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        
        result.append("Name: ")
                .append(taskName)
                .append(",\n");
        result.append("Occurrences: [");
        
        events.stream()
                .sorted()
                .forEach(it -> result.append(it)
                        .append(", "));
        
        result.delete(result.length() - 2, result.length());
        result.append("].");
        
        return result.toString();
    }
}
