package be.kdg.java2.gameproject.kollections.sets;

import be.kdg.java2.gameproject.kollections.Collection;
import be.kdg.java2.gameproject.kollections.lists.List;

public interface Set<E> extends Collection<E> {
    List<E> toList();
}
