package common;

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

    public static boolean arraysEqual(int[] a, int[] b){
        if (a.length != b.length){
            return false;
        }
        //for(int i = 0; i < )
        return true;
    }

}
