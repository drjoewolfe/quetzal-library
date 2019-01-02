package org.jwolfe.quetzal.library.trips;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Guide {
    public static void heading(String heading) {
        System.out.println(heading);
        for (int i = 0; i < heading.length(); i++) {
            System.out.print("*");
        }

        System.out.println();
    }

    public static void section(String sectionHeader) {
        System.out.println(sectionHeader);
    }

    public static void endSection() {
        System.out.println();
    }

    public static void log(String message) {
        System.out.println("\t- " + message);
    }

    public static <T> void log(Stream<T> stream) {
        var elements = stream.collect(Collectors.toList());
        System.out.print("\t");
        for(var item : elements) {
            System.out.print(item + "\t");
        }

        System.out.println();
    }

    public static <T> void log(String title, Stream<T> stream) {
        System.out.print("\t- " + title);
        log(stream);
    }

    public static <T> void log(Stream<T> stream, int count, String separator) {
        var elements = stream
                .limit(count)
                .collect(Collectors.toList());
        System.out.print("\t");
        for(var item : elements) {
            System.out.print(item + separator + " ");
        }

        System.out.println();
    }

    public static <T> void log(String title, Stream<T> stream, int count, String separator) {
        System.out.print("\t- " + title);
        log(stream, count, separator);
    }
}
