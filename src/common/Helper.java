package common;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
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

    public static int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public static int getCountNonNull(int[] numbers){
        int count = 0;
        for(int i : numbers){
            if (i != 0 && i != 1){
                count += 1;
            }
        }
        return count;
    }

    /**
     * loads the title text file
     * @param title
     * @return
     * @throws IOException
     */
    public static String loadText(String title) throws IOException {
        String path = System.getProperty("user.dir") + "/res/" + title;
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        }
        finally {
            stream.close();
        }
    }

}
