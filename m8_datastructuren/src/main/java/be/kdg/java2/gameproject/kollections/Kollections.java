package be.kdg.java2.gameproject.kollections;

import be.kdg.java2.gameproject.kollections.lists.ArrayList;
import be.kdg.java2.gameproject.kollections.lists.List;

public class Kollections {

    private Kollections(){}
    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int indexSmallest = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(indexSmallest)) < 0) {
                    indexSmallest = j;
                }
            }
            T tmp = list.get(i);
            list.set(i, list.get(indexSmallest));
            list.set(indexSmallest, tmp);
        }
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        //splits in 2 delen, sorteer elk deel en merge dan
        mergeSort(list, 0, list.size() - 1);
    }

    //from inclusive, to inclusive
    private static <T extends Comparable<T>> void mergeSort(List<T> list, int from, int to) {
        //splits in 2 delen, sorteer elk deel en merge dan
        if (from > to) throw new IllegalArgumentException("from should be before to");
        if (to - from == 0) return;//trivial case

        mergeSort(list, from, from + (to-from) / 2); // linker heflt
        //het andere deel
        mergeSort(list, from + (to-from)/ 2, to); //rechter helft
        //2 dellen samenvoegen
        merge(list, from, to);
    }

    //from and to inclusive
    private static <T extends Comparable<T>> void merge(List<T> list, int from, int to) {
        List<T> mergedList = new ArrayList<>();
        int startSecondList = from + (to - from) / 2 + 1;
        int index1 = from;
        int index2 = startSecondList;
        while (index1 < startSecondList && index2 <= to) {
            if (list.get(index1).compareTo(list.get(index2)) < 0) {
                mergedList.add(list.get(index1++));
            } else {
                mergedList.add(list.get(index2++));
            }
        }
        if (index1 == startSecondList) {//copy rest of second list
            while (index2 <= to) {
                mergedList.add(list.get(index2++));
            }
        } else {//copy rest of first list
            while (index1 < startSecondList) {
                mergedList.add(list.get(index1++));
            }
        }
        for (int i = from; i <= to; i++) {
            list.set(i, mergedList.get(i - from));
        }
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        quickSort(list, 0, list.size());
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int start, int end) {
        if (end - start <= 0) return;
        int i = start;
        int j = end - 1;
        boolean movingI = true;
        while (i < j) {
            if (list.get(i).compareTo(list.get(j)) > 0) {
                //swap(list, i, j);
                T tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                movingI = !movingI;
            } else {
                if (movingI) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        quickSort(list, start, i);
        quickSort(list, i + 1, end);
    }

    public static <T> int lineairSearch(List<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element) {
        return binarySearch(sortedList, element, 0, sortedList.size());
    }

    private static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element, int from, int to) {
        if (from > to) return -1; //het zit er niet indus hetgene dat je zoekt

        int midden = from + (to - from) /2;
        int comparison = sortedList.get(midden).compareTo(element);
        if (comparison == 0){ // resultaat gevonden
            return midden;
        } else if (comparison > 0) { // we moeten verder gaan met die rechter helft
            return binarySearch(sortedList, element, from, midden - 1); //midde zelf hebben we al vergeleken drm -1
        }else { // linker helft
            return binarySearch(sortedList, element, midden +1, to);
        }
    }
}
