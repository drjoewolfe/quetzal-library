package org.jwolfe.quetzal.library.utilities;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.Comparator;

public class PairFirstSorter implements Comparator<Pair<Integer, Integer>> {
    public PairFirstSorter() {

    }

    @Override
    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
        int a = o1.getFirst();
        int b = o2.getFirst();

        return a - b;
    }
}