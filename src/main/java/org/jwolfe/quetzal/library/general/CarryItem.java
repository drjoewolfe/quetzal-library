package org.jwolfe.quetzal.library.general;

import java.util.Objects;

public class CarryItem {
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    int weight;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public CarryItem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarryItem carryItem = (CarryItem) o;
        return weight == carryItem.weight &&
                value == carryItem.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, value);
    }
}
