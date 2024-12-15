package be.kdg.java2.gameproject.generics;

import java.util.*;

public class PriorityQueue<T> implements FIFOQueue<T> {
    private Map<Integer, LinkedList<T>> myMap = new TreeMap<>(Comparator.reverseOrder());

    @Override
    public boolean enqueue(T element, int priority) {
        for (var val: myMap.values()) {
            if(val.contains(element))return false;
        }
        if(myMap.containsKey(priority)){
            myMap.get(priority).addLast(element);
        }else{
            myMap.put(priority, new LinkedList<>());
            myMap.get(priority).add(element);
        }
        return true;
    }

    @Override
    public T dequeue() {
        for (var key : myMap.keySet()) {
            if (!myMap.get(key).isEmpty())
                return myMap.get(key).pop();
        }
        return null;
    }

    @Override
    public int search(Object element) {
        for (var key: myMap.keySet()){
            if(myMap.get(key).contains(element)) {
                return key;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        //return myMap.size();
        int i = 0;
        for (var key: myMap.keySet()) {
            for (var item: myMap.get(key)) {
                i++;
            }
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int key : myMap.keySet()) {
            for (var item : myMap.get(key)){
                sb.append(key).append(": ").append(item).append("\n");
            }
        }
        return sb.toString();
    }
}
