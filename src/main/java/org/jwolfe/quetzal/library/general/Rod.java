package org.jwolfe.quetzal.library.general;

import java.util.Stack;

public class Rod {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public Stack<Integer> getDisks() {
        return disks;
    }

    public void setDisks(Stack<Integer> disks) {
        this.disks = disks;
    }

    Stack<Integer> disks;

    public Rod(String name, Stack<Integer> disks) {
        this.name = name;
        this.disks = disks;
    }
}
