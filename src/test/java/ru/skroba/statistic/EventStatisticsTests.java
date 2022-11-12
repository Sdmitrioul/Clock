package ru.skroba.statistic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skroba.clock.StaticClock;
import ru.skroba.event.Event;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.skroba.Util.getEventString;

public class EventStatisticsTests {
    private final ByteArrayOutputStream outputtreamCaptor = new ByteArrayOutputStream();
    private Instant instant;
    private StaticClock clock;
    private Statistics<Event> statistics;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        instant = Instant.now();
        clock = new StaticClock(instant);
        statistics = new EventStatistics(clock);
    }
    
    @Test
    void testWithoutEvent() {
        assertEquals(0.0, statistics.getEventStatisticByName("Random"));
        assertEquals(0.0, statistics.getAllEventStatistic());
        statistics.printStatistic();
        assertEquals("", outputStreamCaptor.toString());
    }
    
    @Test
    void testWithSingleEvent() {
        statistics.incEvent("Test");
        clock.setTime(instant.plus(1, ChronoUnit.SECONDS));
        
        final double result = 1.0 / 60;
        
        assertEquals(result, statistics.getEventStatisticByName("Test"));
        assertEquals(result, statistics.getAllEventStatistic());
        statistics.printStatistic();
        assertEquals(getEventString("Test", result) + "\n", outputStreamCaptor.toString());
    }
    
    @Test
    void testWithTwoEvents() {
        final List<String> eventsName = List.of("Test", "Random");
        
        eventsName.forEach(statistics::incEvent);
        clock.setTime(instant.plus(1, ChronoUnit.SECONDS));
        
        final double result = 1.0 / 60;
        
        for (String event : eventsName) {
            assertEquals(result, statistics.getEventStatisticByName(event));
        }
        
        assertEquals(result * 2, statistics.getAllEventStatistic());
        statistics.printStatistic();
        final Set<String> printedWaited = eventsName.stream()
                .map(it -> getEventString(it, result))
                .collect(Collectors.toSet());
        assertEquals(printedWaited, Arrays.stream(outputStreamCaptor.toString()
                        .split("\n"))
                .collect(Collectors.toSet()));
    }
    
    @Test
    void statisticWithOutdatedEvents() {
        final List<String> eventsName = List.of("Test", "Random");
        
        eventsName.forEach(statistics::incEvent);
        clock.setTime(instant.plus(2, ChronoUnit.HOURS));
        
        final double result = 0.0;
        
        for (String event : eventsName) {
            assertEquals(result, statistics.getEventStatisticByName(event));
        }
        
        assertEquals(result * 2, statistics.getAllEventStatistic());
        statistics.printStatistic();
        final Set<String> printedWaited = eventsName.stream()
                .map(it -> getEventString(it, result))
                .collect(Collectors.toSet());
        assertEquals(printedWaited, Arrays.stream(outputStreamCaptor.toString()
                        .split("\n"))
                .collect(Collectors.toSet()));
    }
    
    @Test
    void statisticWithRandomEvents() {
        final List<String> eventsName = List.of("Test", "Random");
        
        eventsName.forEach(statistics::incEvent);
        instant = instant.plus(2, ChronoUnit.HOURS);
        clock.setTime(instant);
        
        final int count = 3;
        for (int i = 0; i < count; i++) {
            instant = instant.plus(count, ChronoUnit.MINUTES);
            clock.setTime(instant);
            eventsName.forEach(statistics::incEvent);
        }
        
        final double result = (double) (count - 1) / 60;
        
        for (String event : eventsName) {
            assertEquals(result, statistics.getEventStatisticByName(event));
        }
        
        assertEquals(result * 2, statistics.getAllEventStatistic());
        statistics.printStatistic();
        final Set<String> printedWaited = eventsName.stream()
                .map(it -> getEventString(it, result))
                .collect(Collectors.toSet());
        assertEquals(printedWaited, Arrays.stream(outputStreamCaptor.toString()
                        .split("\n"))
                .collect(Collectors.toSet()));
    }
}
