package ab4.aufgabe1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 21.11.12
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public final class HashTester {
    private HashTester(){};



    public static int hash1(String str, int m){
        return Math.abs(str.hashCode() % m);
    }

    /**
     * Summiert einfach die Textwerte aufeinander, die Reihenfolge der Characters ist Egal.
     * @param str
     * @param m
     * @return
     */
    public static int hash2(String str, int m){
        char [] data = str.toCharArray();
        int hash = 0;
        for(char e :data){
            hash += e;
        }
        return Math.abs(hash%m);
    }

    /**
     * Ein bisschen besser als hash2, da hier die Reihenfolge beachtet wird. Es wird ein Zähler eingeführt,
     * der jedesmal zusätzlich mit dem entsprechnden Wert multipliziert wird
     * @param str
     * @param m
     * @return
     */
    public static int hash3(String str, int m){
        char [] data = str.toCharArray();
        long hash = 0;
        int i = 1;
        for (char e : data){
            hash += e * i;
            i++;
        }
        return (int)Math.abs(hash%m);
    }

    /**
     * Shift-Add_XOR
     *
     * @param str
     * @param m
     * @return
     */
    public static int hash4(String str, int m){
        char [] data = str.toCharArray();
        long hash = 0;
        int i = 1;
        for (char e : data){
            hash ^= ( hash << 5) + (hash >>> 2) + e;
        }
        return (int)Math.abs(hash%m);
    }

}
