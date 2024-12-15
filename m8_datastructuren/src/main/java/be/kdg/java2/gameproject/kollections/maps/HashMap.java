package be.kdg.java2.gameproject.kollections.maps;

import be.kdg.java2.gameproject.kollections.lists.ArrayList;
import be.kdg.java2.gameproject.kollections.lists.List;
import be.kdg.java2.gameproject.kollections.sets.ArraySet;
import be.kdg.java2.gameproject.kollections.sets.Set;

public class HashMap<K, V> implements Map<K, V>{
    private static final int DEFAULT_CAPACITY = 100; // size van bucket array dus array van 100 buckets

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next; //referentie naar volgende node om koppeling te creeren

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key); //op basis van die hash kom je bij de juiste bucket array
        Node<K, V> nieuweNode = new Node<>(key, value); // een node van maken
        //of wel is bucket leeg of wel zit er een node in
        if(buckets[hash] == null){ //bucket is leeg
            buckets[hash] = nieuweNode; //krijgt referentie naar eerste node
        }else { //bucket is niet leeg dus bevat een referentie naar eerste node
            // vroegere eerste node wijst naar de nieuwe eerste node
            nieuweNode.next = buckets[hash];
            // en de nieuwe eerste moet wijzen naar de oude eerste
            buckets[hash] = nieuweNode;
        }
        size++;


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        //eerst hash berekenen  zodat je in de juiste array van buckets kan komen
        int hash = hash(key);
        // nu ben je in die bucket ma je moet beginnen te schuiven want er zijn meerdere buckets in die array
        Node<K, V> node = buckets[hash];
        while (node.next != null){ //doorlopen
            if (node.key.equals(key)){//als je die bucket hebt gevonden
                return node.value;
            }
            node = node.next; // zo schuif je door heen die trein
        }

        return null; // als hij die niet vind
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }
}
