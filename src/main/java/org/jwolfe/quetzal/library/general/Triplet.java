package org.jwolfe.quetzal.library.general;

public class Triplet<T1, T2, T3> {
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

    public Triplet() {

    }

    public Triplet(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Triplet{");
        sb.append("first=").append(first);
        sb.append(", second=").append(second);
        sb.append(", third=").append(third);
        sb.append('}');
        return sb.toString();
    }
}