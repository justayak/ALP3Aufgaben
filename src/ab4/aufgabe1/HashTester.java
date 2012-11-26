package ab4.aufgabe1;

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

    /**
     * Summiert modulo einer primzahl auf jeden charcter + index. Schwach, da der Modulo die Varianz stark beschränkt.
     * Reihenfolge wird beachtet
     * @param str
     * @param m
     * @return
     */
    public static int hash5(String str, int m){
        char [] data = str.toCharArray();
        long hash = 0;
        int prim = 7;
        int i = 0;
        for(char e : data){
            hash += (e + i) % prim;
            i++;
        }
        return (int)hash%m;
    }

    /**
     * Shiftet jeden einzelnen Wert abhängig von seiner Position im String. Dadurch entsteht
     * eine relativ gute Durchmischung (im Vergleich zu den anderen Funktionen)
     * @param str
     * @param m
     * @return
     */
    public static int hash6(String str, int m){
        char [] data = str.toCharArray();
        int maxShift = 9; // shift one round of integer 00000000
        int hash = 0;
        int i = 0;
        for (char c : data){
            hash += c << (i%maxShift);
            i++;
        }
        return Math.abs(hash)%m;
    }

}
