package org.jwolfe.quetzal.library.trips;

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
}
