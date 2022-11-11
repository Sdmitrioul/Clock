package ru.skroba.statistic;

public interface Statistics<T> {
    
    void incEvent(String name);
    
    double getAllEventStatistic();
    
    double getEventStatisticByName(String name);
    
    void printStatistic();
}
