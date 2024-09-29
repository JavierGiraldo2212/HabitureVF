package com.habiture.Structures.NonLinear;

import java.util.HashMap;
import java.util.Map;

public class GenericDisjointSet<T> {

    private Map<T, T> parent;
    private Map<T, Integer> rank;

    public GenericDisjointSet() {
        parent = new HashMap<>();
        rank = new HashMap<>();
    }

    // Inicializa un nuevo conjunto con un único elemento
    public void makeSet(T item) {
        if (!parent.containsKey(item)) {
            parent.put(item, item);  // El elemento es su propio padre
            rank.put(item, 0);       // Inicializamos el rango a 0
        }
    }

    public T find(T item) {
        if (!parent.get(item).equals(item)) {
            parent.put(item, find(parent.get(item))); // Path compression
        }
        return parent.get(item);
    }

    public void union(T item1, T item2) {
        T root1 = find(item1);
        T root2 = find(item2);

        if (root1.equals(root2)) {
            return; // Ya están en el mismo conjunto
        }

        // Union by rank
        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root1, root2);
            if (rank.get(root1).equals(rank.get(root2))) {
                rank.put(root2, rank.get(root2) + 1);
            }
        }
    }

    public boolean isConnected(T item1, T item2) {
        return find(item1).equals(find(item2));
    }
}
