package org.example;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final List<Object> values = new ArrayList<>();
    private double sum = 0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;

    public void add(int value) {
        values.add(value);
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public void add(double value) {
        values.add(value);
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public void add(String value) {
        values.add(value);
        min = Math.min(min, value.length());
        max = Math.max(max, value.length());
    }

    public List<?> getValues() {
        return values;
    }

    public String getSummary(int stat) {
        if (values.isEmpty()) return "0 elements";

        if (stat == 2) {
            if (values.getFirst() instanceof Integer || values.getFirst() instanceof Double) {
                return String.format("%d elements, min: %.2f, max: %.2f, sum: %.2f, avg: %.2f",
                        values.size(), min, max, sum, sum / values.size());
            } else {
                return String.format("%d elements, shortest: %d, longest: %d",
                        values.size(), (int) min, (int) max);
            }
        } else {
            return values.size() + " elements";
        }
    }
}
