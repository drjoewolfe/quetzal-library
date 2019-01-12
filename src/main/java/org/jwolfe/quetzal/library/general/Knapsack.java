package org.jwolfe.quetzal.library.general;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    public List<CarryItem> getItems() {
        return items;
    }

    List<CarryItem> items;

    public Knapsack() {
        items = new ArrayList<>();
    }

    public Knapsack(List<CarryItem> items) {
        this.items = items;
    }

    public int getWeight() {
        int weight = items.stream().mapToInt(item -> item.weight).sum();
        return weight;
    }

    public int getValue() {
        int value = items.stream().mapToInt(item -> item.value).sum();
        return value;
    }
}
