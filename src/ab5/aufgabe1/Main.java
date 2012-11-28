package ab5.aufgabe1;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 26.11.12
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args){

        int size = 10000;
        Integer[] list = new Integer[size];
        Random random = new Random();

        System.out.println("Generating...");
        final int STEP = 10000;
        for (int i = 0; i < list.length; i++){
            //list[i] = (int)Math.pow(2,i);
            //list[i] = i;
            //list[i] = i % 2 == 0 ? i : i - 1;
            int value = random.nextInt(100);
            list[i] = value % 2 == 0 ? value : value + 1;
            if (i % STEP == 0){
                System.out.println("reached:" + i);
            }
        }
        Arrays.sort(list);
        System.out.println("Generated");
        int searchItem = 12;
        System.out.println("Suche nach: " + searchItem + " Listengröße: " + size);

        long startBinary = System.nanoTime();
        Object result = binarySearch(list,searchItem);
        long endBinary = System.nanoTime();
        long dtBinary = endBinary - startBinary;
        System.out.println("#1:elapsed:" + dtBinary + " nanos >recursion depth:" + PERFORMANCE_RECURSION_DEPTH + " > FOUND: " + result + " [Binärsuche]");

        //Long dtInterp = new Long(0);
        long[] dtInterp = new long[1];
        int resultI = interpolationSearch(list,searchItem, dtInterp);
        System.out.println("#2:elapsed:" + dtInterp[0] + " nanos >recursion depth:" + PERFORMANCE_RECURSION_DEPTH_INTER + " > FOUND: " + resultI + " [Interpolationssuche]");
    }

    /*
======================================================================
S T A T I S T I C S
======================================================================
Suche nach: 12 Listengröße: 1.000
#1:elapsed:363.177 nanos >recursion depth:5 > FOUND: 12 [Binärsuche]
#2:elapsed:6.792 nanos >recursion depth:2 > FOUND: 12 [Interpolationssuche]

Suche nach: 12 Listengröße: 10.000
#1:elapsed:499.976 nanos >recursion depth:3 > FOUND: 12 [Binärsuche]
#2:elapsed:3.881 nanos >recursion depth:1 > FOUND: 12 [Interpolationssuche]

Suche nach: 12 Listengröße: 100.000
#1:elapsed:646.153 nanos >recursion depth:3 > FOUND: 12 [Binärsuche]
#2:elapsed:9.055 nanos >recursion depth:1 > FOUND: 12 [Interpolationssuche]

Suche nach: 12 Listengröße: 1.000.000
#1:elapsed:2.706.856 nanos >recursion depth:3 > FOUND: 12 [Binärsuche]
#2:elapsed:34.604 nanos >recursion depth:1 > FOUND: 12 [Interpolationssuche]

Suche nach: 12 Listengröße: 10.000.000
#1:elapsed:26.150.753 nanos >recursion depth:3 > FOUND: 12 [Binärsuche]
#2:elapsed:51.097 nanos >recursion depth:1 > FOUND: 12 [Interpolationssuche]

Legende:
* = Interpolation
+ = Binär

    y (nanos in 1000)
    ^
2500|                       +
2000|               +
----------------------------------------------------------------------------------
1325|
1225|
1025|
825 |
625 |         +
425 |      +
-------------------------------------------------------------------------------
325 | +
325 |
300 |
275 |
250 |
225 |
200 |
150 |
125 |
100 |
 75 |
 50 |                          *
 25 | *    *    *      *
    #-1- -10- -100- -1.000- -10.000- --> x (listsize in 1000)

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

    public static int interpolationSearch(Integer[] list, int item, long[] dt){
        // convert..
        int[] r = new int[list.length];
        for(int i = 0; i < list.length; i++){
            r[i] = list[i];
        }
        long startInterp = System.nanoTime();
        int result = interpolationSearch(r,item);
        long endInterp = System.nanoTime();
        dt[0] = endInterp - startInterp;
        return result;
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
