package be.kdg.java2.gameproject.kollections.sets;

import be.kdg.java2.gameproject.kollections.Kollections;
import be.kdg.java2.gameproject.kollections.lists.ArrayList;
import be.kdg.java2.gameproject.kollections.lists.List;

public class ArraySet<T> implements Set<T> {
    private List<T> elements;

    public ArraySet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        if (Kollections.lineairSearch(elements, element)==-1) { //-1 het is niet gevonden in die set
            elements.add(element);
        }
    }

    @Override
    public boolean remove(T element) { //delegatie
        return elements.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public List<T> toList() {
        return elements;
    }
}
