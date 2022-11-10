package ru.skroba.statistic;

import ru.skroba.event.NamedEvent;

import java.time.Instant;
import java.util.List;

public interface Statistics {
    
    void incEvent(String name);
    
    List<NamedEvent> getAllEventStatistic();
    
    List<Instant> getEventStatisticByName(String name);
    
    void printStatistic();
}
