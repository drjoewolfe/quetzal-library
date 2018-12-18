package org.jwolfe.quetzal.library.general;

import java.util.Objects;

public class Quadruplet<T1, T2, T3, T4> {

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    T1 first;

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    T2 second;

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }

    T3 third;

    public T4 getFourth() {
        return fourth;
    }

    public void setFourth(T4 fourth) {
        this.fourth = fourth;
    }

    T4 fourth;

    public Quadruplet() {
    }

    public Quadruplet(T1 first, T2 second, T3 third, T4 fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        return "Quadruplet{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", fourth=" + fourth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadruplet<?, ?, ?, ?> that = (Quadruplet<?, ?, ?, ?>) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second) &&
                Objects.equals(third, that.third) &&
                Objects.equals(fourth, that.fourth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, second, third, fourth);
    }
}
