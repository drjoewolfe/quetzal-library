package org.jwolfe.quetzal.library.utilities;

import org.jwolfe.quetzal.library.general.Activity;

import java.util.Comparator;

public class ActivityStartComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity o1, Activity o2) {
        return o1.getStart() - o2.getStart();
    }
}

