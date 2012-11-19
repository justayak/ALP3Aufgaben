package common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * ALP III - Julian
 * <p/>
 * User: Baka
 * Date: 06.11.12
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class Helper {

    /**
     * reverse an int array
     * @param array
     * @return
     */
    public static int[] reverse(int [] array){
        int[] result = new int[array.length];
        for(int i = 0, j = array.length -1; i < array.length; i++, j--){
            result[j] = array[i];
        }
        return result;
    }

    /**
     * checks if two arrays have same content
     * @param a
     * @param b
     * @return
     */
    public static boolean arraysEqual(int[] a, int[] b){
        if (a.length != b.length){
            return false;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    public static void swap (int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * serializes an object to an byte[]
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try{
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
        }catch (IOException e){
            System.out.println("shit!");
        }
        return out.toByteArray();
    }

}
