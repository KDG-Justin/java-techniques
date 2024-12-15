package be.kdg.java2.gameproject.kollections.maps;

import be.kdg.java2.gameproject.kollections.Collection;
import be.kdg.java2.gameproject.kollections.sets.Set;

public interface Map<K, V>{
    void put(K key, V value);
    V get(K key);
    Collection<V> values();
    Set<K> keySet();
    int size();
}
