package be.kdg.java2.gameproject.kollections.lists;

import be.kdg.java2.gameproject.kollections.Collection;

public interface List<E> extends Collection<E> {
    void add(int index, E element);
    void set(int index, E element);
    E remove(int index);
    E get(int index);
    int indexOf(E element);
}
