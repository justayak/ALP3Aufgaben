package ab5.aufgabe1;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 26.11.12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args){
        int size = 20;
        Integer[] list = new Integer[size];
        for (int i = 0; i < list.length; i++){
            //list[i] = (int)Math.pow(2,i);
            //list[i] = i;
            list[i] = i % 2 == 0 ? i : i - 1;
        }
        int searchItem = 5;
        System.out.println("Suche nach: " + searchItem + " Listengröße: " + size);
        System.out.println("#1:" + binarySearch(list,searchItem) + " >recursion depth:" + PERFORMANCE_RECURSION_DEPTH + " [Binärsuche]");
        System.out.println("#2:" + interpolationSearch(list,searchItem) + " >recursion depth:" + PERFORMANCE_RECURSION_DEPTH_INTER + " [Interpolationssuche]");
    }

    /*
======================================================================
S T A T I S T I C S
======================================================================
*********************************************
*********************************************
fill-function: A[n] = 2^n
*********************************************
Suche nach: 128 Listengröße: 10
#1:128 >recursion depth:3 [Binärsuche]
#2:128 >recursion depth:4 [Interpolationssuche]

Suche nach: 128 Listengröße: 50
#1:128 >recursion depth:6 [Binärsuche]
#2:128 >recursion depth:8 [Interpolationssuche]

Suche nach: 128 Listengröße: 200
#1:128 >recursion depth:8 [Binärsuche]
#2:128 >recursion depth:8 [Interpolationssuche]

Suche nach: 128 Listengröße: 500
#1:128 >recursion depth:6 [Binärsuche]
#2:128 >recursion depth:8 [Interpolationssuche]
*********************************************
*********************************************
fill-function: A[n] = n
*********************************************
Suche nach: 4 Listengröße: 10
#1:4 >recursion depth:3 [Binärsuche]
#2:4 >recursion depth:1 [Interpolationssuche]

Suche nach: 4 Listengröße: 50
#1:4 >recursion depth:6 [Binärsuche]
#2:4 >recursion depth:1 [Interpolationssuche]

Suche nach: 4 Listengröße: 500
#1:4 >recursion depth:9 [Binärsuche]
#2:4 >recursion depth:1 [Interpolationssuche]

*********************************************
fill-function: A[n] = { grade: i, ungrade: i-1}
*********************************************
Suche nach: 6 Listengröße: 500
#1:6 >recursion depth:6 [Binärsuche]
#2:6 >recursion depth:1 [Interpolationssuche]

/nicht findbares item:
Suche nach: 5 Listengröße: 20
#1:null >recursion depth:5 [Binärsuche]
#2:-1 >recursion depth:2 [Interpolationssuche]

Suche nach: 6 Listengröße: 20
#1:6 >recursion depth:4 [Binärsuche]
#2:6 >recursion depth:1 [Interpolationssuche]

     */

    static int PERFORMANCE_RECURSION_DEPTH = 0;
    static int PERFORMANCE_RECURSION_DEPTH_INTER = 0;

    /**
     * rekursive Binärsuche. Es wird im Fall des nicht-findens null zurückgegeben und KEINE Exception geworfen da
     * das nicht-vorhanden-sein eines Elements in einer Liste bei einer Suche zum normalen Programmfluss gehört und
     * deshalb nicht Exception-würdig ist.
     * @param list die Liste, aus der gesucht wird. Die Liste MUSS aufsteigend sortiert sein!
     * @param item das gesuchte Item
     * @param <T> beliebiger Datentyp, der Comparable implementiert
     * @return das gesuchte Item, wenn gefunden, ansonsten null
     */
    public static <T extends Comparable<T>> T binarySearch(T[] list, T item){
        PERFORMANCE_RECURSION_DEPTH++;
        switch (list.length){
            case 0:
                break;
            case 1:
                return list[0].equals(item) ? list[0] : null;
            default:
                int median = (int)Math.ceil(list.length / 2);
                if (list[median].compareTo(item) < 0){ // median is smaller then item
                    T[] right = Arrays.copyOfRange(list, median + 1, list.length);
                    return binarySearch(right, item);
                }else if (list[median].compareTo(item) > 0){ // median is bigger then item
                    T[] left = Arrays.copyOfRange(list,0,median);
                    return binarySearch(left, item);
                }else if(list[median].compareTo(item) == 0) { // median
                    return list[median];
                }
                break;
        }
        return null;
    }

    public static int interpolationSearch(Integer[] list, int item){
        // convert..
        int[] r = new int[list.length];
        for(int i = 0; i < list.length; i++){
            r[i] = list[i];
        }
        return interpolationSearch(r,item);
    }

    /**
     * Rekursive Interpolationssuche
     * @param list vorsortierte Liste
     * @param item das gesuchte item
     * @return liefert das gesuchte Item oder -1, wenn nicht gefunden
     */
    public static int interpolationSearch(int[] list, int item){
        PERFORMANCE_RECURSION_DEPTH_INTER++;
        if (item >= list[0] && item <= list[list.length-1]){
            int diff = list[list.length-1] - list[0];
            int median = (int) ((double)list.length * (item - list[0]) / diff);
            if (item > list[median]){
                return interpolationSearch(Arrays.copyOfRange(list,median + 1,list.length), item);
            }else if(item < list[median]){
                return interpolationSearch(Arrays.copyOfRange(list,0,median - 1), item);
            }else {
                return list[median];
            }
        }else {
            return -1;
        }
    }

}
