package ab2.aufgabe1.a;

import common.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * ALP III - Julian
 * <p/>
 * User: Baka
 * Date: 06.11.12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class main {
    public static void main(String[] args){

        int[] test = new int[]{5,4,3,2,1};
        int[] result = slowSort(test);

        for(int i = 0; i < result.length; i++){
            int cur = result[i];
            System.out.println(i + ":" + cur);
        }


    }

    /**
     * performs a slow sort on the target array and returns the sorted array
     * @param array unsorted array
     * @return sorted array
     */
    private static int[] slowSort(int[] array){
        if (isSorted(array)){
            // array has 0 or 1 elements or is already sorted
            return array;
        }

        List<int[]> perms = permutations(array);
        for(int[] perm : perms){
            if (isSorted(perm)){
                return perm;
            }
        }
        throw new NoSuchFieldError("Wir tun so als sei das die <KeinePermutationGefunden>Exception");
    }

    private static void swap (int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * generates all permutations of the given array
     * @param array
     * @return
     */
    public static List<int[]> permutations (int[] array){
        int endIndex = array.length-1;
        List<int[]> accumulator = new ArrayList<int[]>();
        permutations(array, endIndex, accumulator);
        return accumulator;
    }

    /**
     * generates all permutations recursivly
     * @param array
     * @param endIndex
     * @param accumulator
     */
    private static void permutations (int[] array, int endIndex, List<int[]> accumulator){
        if(endIndex==0){
            accumulator.add((int[])array.clone());
        } else{
            permutations(array, endIndex -1, accumulator);
            for(int i = 0; i <= endIndex-1; i++){
                swap(array,i,endIndex);
                permutations(array, endIndex-1, accumulator);
                swap(array,i,endIndex);
            }
        }
    }

    /**
     * checks, if the array is sorted
     * @param array
     * @return TRUE when sorted, else false
     */
    private static boolean isSorted(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i-1] > array[i]){
                return false;
            }
        }
        return true;
    }
}
