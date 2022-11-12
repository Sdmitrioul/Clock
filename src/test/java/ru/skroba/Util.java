package ru.skroba;

public class Util {
    public static String getEventString(String name, double value) {
        return  "[Name: " + name + "; rpm: " + String.format("%.2f]", value);
    }
}
