package org.jwolfe.quetzal.trips;

import org.jwolfe.quetzal.library.trips.Trip;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.Arrays;
import java.util.List;

import static org.jwolfe.quetzal.library.trips.Guide.*;

public class Streams implements Trip {
    @Override
    public void tour() {
        heading("Java Streams");

        section("Preparations");
        String text = Utilities.getTextFromResourceFile("files/lit.txt");
        List<String> words = Arrays.asList(text.split("\\PL+"));
        log("loaded words");
        endSection();

        section("Basic Streaming");
        long count = words.stream()
                .filter(w -> w.length() > 10)
                .count();
        log("net word count: " + words.size());
        log("count of words with length > 10: " + count);
        endSection();
    }
}
