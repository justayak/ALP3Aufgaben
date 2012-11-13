package ab3.aufgabe1;

import common.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 11.11.12
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main (String[] args){
        int size = 10000;

        int[] arr = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            arr[i] = random.nextInt(100);
        }

        for(int i = 1; i < size; i++){
            System.out.println(i + "rec>" + select(arr, i));
        }
    }

    /**
     * Laufzeit
     * Mit jedem Durchlauf halbieren wir den Suchraum. Im ersten Schritt arbeiten wir mit allen Items N. Im nächsten
     * Schritt nur noch mit ungefähr der Hälfte, N/2, und so weiter.
     * Laufzeit = n + n/2 + n/4 ...
     * Laufzeit - (Laufzeit/2) = n
     *
     * @param a the target array
     * @param k th element should be selected
     * @return the k-th element
     */
    public static int select(int[]a,int k){
        List<Integer> list = new ArrayList<Integer>(a.length);
        for(int i = 0; i < a.length; i++){
            list.add(a[i]);
        }
        return select(list,k);
    }

    /**
     * Implemented as described in lecture
     * @param a the target list
     * @param k th element should be selected
     * @return
     */
    private static int select(List<Integer>a,int k){
        int pivot = a.get((a.size()-1)/2);
        List<Integer> smaller = new ArrayList<Integer>();
        List<Integer> equal = new ArrayList<Integer>();
        List<Integer> bigger = new ArrayList<Integer>();
        for(int i = 0; i < a.size(); i++){
            if(a.get(i)>pivot){
                bigger.add(a.get(i));
            }else if (a.get(i)<pivot){
                smaller.add(a.get(i));
            }else {
                equal.add(a.get(i));
            }
        }
        if (k <= smaller.size()){
            return select(smaller,k);
        }else if(k <= (smaller.size()+equal.size())){
            return pivot;
        } else {
            k = k - smaller.size() - equal.size();
            return select(bigger,k);
        }
    }
}

