package org.jwolfe.quetzal.library.set;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
    Map<T, T> parent;

    public DisjointSet() {
        this.parent = new HashMap<>();
    }

    public void makeSet(T item) {
        if (item == null) {
            return;
        }

        parent.put(item, null);
    }

    public T find(T item) {
        if (!parent.containsKey(item)) {
            return null;
        }

        var parentItem = parent.get(item);
        if (parentItem == null) {
            return item;
        }

        return find(parentItem);
    }

    public void union(T item1, T item2) {
        if (item1 == null || item2 == null || item1 == item2) {
            return;
        }

        T i1Root = find(item1);
        T i2Root = find(item2);

        parent.put(i2Root, i1Root);
    }
}