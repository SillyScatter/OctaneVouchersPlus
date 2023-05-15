package com.octane.sllly.octanevouchersplus;

import java.util.HashMap;
import java.util.Map;

public class ChancePicker<T> {

    private Map<T, Integer> options = new HashMap<>();
    private int max = 0;

    public void addOption(T option, double proportionalChance) {
        options.put(option, (int) (proportionalChance * 100));
        max += (int) (proportionalChance * 100);
    }

    public void removeOption(T option){
        max -= options.get(option);
        options.remove(option);
    }

    public T pick() {
        int rng = getRandomNumber(1, max);
        int current = 0;
        for (T option : options.keySet()){
            current += options.get(option);
            if (rng <= current) return option;
        }
        return null;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }
}