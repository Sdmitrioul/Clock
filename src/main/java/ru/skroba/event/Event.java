package ru.skroba.event;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Event {
    boolean put(Instant instant);
    List<Instant> getTimes();
    List<Instant> getFiltered(int time, TimeUnit unit);
}
