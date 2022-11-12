# Clock pattern

Repository was created for Software Design course in ITMO (CT) university by
Skroba Dmitriy.

### Purpose:

Gain practical experience in applying the Clock pattern when implementing tests.

### What`s has been done:

1. Created [clock](./src/main/java/ru/skroba/clock/Clock.java) interface.
2. Created to
   realization, [Static clock](./src/main/java/ru/skroba/clock/StaticClock.java)
   where user can set time clock shows(for testing purposes)
   and [RealClock](./src/main/java/ru/skroba/clock/RealClock.java).
3. Created [Event](./src/main/java/ru/skroba/event/Event.java) with
   implementation to store event occurrences.
4. Created [Statistics](./src/main/java/ru/skroba/statistic/Statistics.java)
   store with implementation to save action events occurrences.
5. Created tests for Statistic implementation that shows ability that clock
   interface giving for developer.