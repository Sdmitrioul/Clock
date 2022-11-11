package ru.skroba.statistic;

import ru.skroba.clock.Clock;
import ru.skroba.event.Event;
import ru.skroba.event.NamedEvent;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EventStatistics implements Statistics<Event> {
    private final Clock clock;
    private final Map<String, Event> events = new HashMap<>();
    
    public EventStatistics(final Clock clock) {
        this.clock = clock;
    }
    
    @Override
    public void incEvent(final String name) {
        Event event = events.get(name);
        
        if (event == null) {
            event = new NamedEvent(name);
            events.put(name, event);
        }
        
        event.put(clock.now());
    }
    
    @Override
    public double getAllEventStatistic() {
        final Instant now = clock.now();
        return events.values()
                .stream()
                .mapToDouble(event -> event.getRPM(now, 1L, TimeUnit.HOURS))
                .sum();
    }
    
    @Override
    public double getEventStatisticByName(final String name) {
        final Event event = events.get(name);
        return event == null ? 0 : event.getRPM(clock.now(), 1L, TimeUnit.HOURS);
    }
    
    @Override
    public void printStatistic() {
        final StringBuilder result = new StringBuilder();
        final Instant now = clock.now();
        
        events.forEach((name, event) -> result.append(event.toString(now, 1L, TimeUnit.HOURS))
                .append("\n"));
        
        System.out.print(result);
    }
}
