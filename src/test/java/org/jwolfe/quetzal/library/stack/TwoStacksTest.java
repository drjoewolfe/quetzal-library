package org.jwolfe.quetzal.library.stack;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.stack.TwoStacks;

import static org.junit.jupiter.api.Assertions.*;

class TwoStacksTest {
    @Test
    void TwoStacksTest() {
        TwoStacks stacks = new TwoStacks(5);
        stacks.push1(5);
        stacks.push2(10);
        stacks.push2(15);
        stacks.push1(11);
        stacks.push2(7);
        assertEquals(11, stacks.pop1());
        assertEquals(7, stacks.pop2());

        stacks.push2(40);
        assertEquals(5, stacks.pop1());
        assertEquals(40, stacks.pop2());
    }
}