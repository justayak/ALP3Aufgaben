package ab4.aufgabe1;

import common.Eintrag;
import common.Helper;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 19.11.12
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main (String[]args){


        String[] list = TextSlave.getWords();

        int m = 1000;

        int[] h1Collisions = new int[m];
        int[] h2Collisions = new int[m];
        int[] h3Collisions = new int[m];
        int[] h4Collisions = new int[m];
        int[] h5Collisions = new int[m];
        int[] h6Collisions = new int[m];


        for(String string : list){
            int hash1 = HashTester.hash1(string, m);
            h1Collisions[hash1] += 1;

            int hash2 = HashTester.hash2(string, m);
            h2Collisions[hash2] += 1;

            int hash3 = HashTester.hash3(string, m);
            h3Collisions[hash3] += 1;

            int hash4 = HashTester.hash4(string, m);
            h4Collisions[hash4] += 1;

            int hash5 = HashTester.hash5(string, m);
            h5Collisions[hash5] += 1;

            int hash6 = HashTester.hash6(string, m);
            h6Collisions[hash6] += 1;
        }

        System.out.println("Buckets: " + m + " || Element count:" + list.length);
        System.out.println("#1: Number of total Collisions: " + Helper.getCountNonNull(h1Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h1Collisions));
        System.out.println("#2: Number of total Collisions: " + Helper.getCountNonNull(h2Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h2Collisions));
        System.out.println("#3: Number of total Collisions: " + Helper.getCountNonNull(h3Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h3Collisions));
        System.out.println("#4: Number of total Collisions: " + Helper.getCountNonNull(h4Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h4Collisions));
        System.out.println("#5: Number of total Collisions: " + Helper.getCountNonNull(h5Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h5Collisions));
        System.out.println("#6: Number of total Collisions: " + Helper.getCountNonNull(h6Collisions) + " >> Most Collisions: " + Helper.getMaxValue(h6Collisions));

        /* S T A T I S T I C S ! ! ! !

Buckets: 40 || Element count:5040
#1: Number of total Collisions: 20 >> Most Collisions: 367
#2: Number of total Collisions: 1 >> Most Collisions: 5040
#3: Number of total Collisions: 40 >> Most Collisions: 240
#4: Number of total Collisions: 40 >> Most Collisions: 149 * Performnt am besten
#5: Number of total Collisions: 4 >> Most Collisions: 2580
#6: Number of total Collisions: 40 >> Most Collisions: 151

        Buckets: 100 || Element count:5040
#1: Number of total Collisions: 50 >> Most Collisions: 152
#2: Number of total Collisions: 1 >> Most Collisions: 5040
#3: Number of total Collisions: 45 >> Most Collisions: 240
#4: Number of total Collisions: 100 >> Most Collisions: 67
#5: Number of total Collisions: 4 >> Most Collisions: 2580
#6: Number of total Collisions: 100 >> Most Collisions: 65 * Performnt am besten

Buckets: 400 || Element count:5040
#1: Number of total Collisions: 195 >> Most Collisions: 60
#2: Number of total Collisions: 1 >> Most Collisions: 5040
#3: Number of total Collisions: 45 >> Most Collisions: 240
#4: Number of total Collisions: 400 >> Most Collisions: 27
#5: Number of total Collisions: 4 >> Most Collisions: 2580
#6: Number of total Collisions: 400 >> Most Collisions: 21 * Performnt am besten

Buckets: 1000 || Element count:5040
#1: Number of total Collisions: 500 >> Most Collisions: 21
#2: Number of total Collisions: 1 >> Most Collisions: 5040
#3: Number of total Collisions: 45 >> Most Collisions: 240
#4: Number of total Collisions: 994 >> Most Collisions: 14
#5: Number of total Collisions: 4 >> Most Collisions: 2580
#6: Number of total Collisions: 936 >> Most Collisions: 17

Buckets: 2400 || Element count:5040
#1: Number of total Collisions: 576 >> Most Collisions: 29
#2: Number of total Collisions: 1 >> Most Collisions: 5040
#3: Number of total Collisions: 45 >> Most Collisions: 240
#4: Number of total Collisions: 2107 >> Most Collisions: 9
#5: Number of total Collisions: 4 >> Most Collisions: 2580
#6: Number of total Collisions: 1014 >> Most Collisions: 17

         */


        /*
        Woerterbuch<String,String> wb = new Woerterbuch<String, String>();
        Eintrag<String, String> eintrag = new Eintrag<String, String>("lol", "hallo");
        Eintrag<String, String> eintrag1 = new Eintrag<String, String>("lol", "hallo1");
        Eintrag<String, String> eintrag2 = new Eintrag<String, String>("how", "hallo2");
        wb.einfuege(eintrag);
        wb.einfuege(eintrag1);
        wb.einfuege(eintrag2);
        System.out.println(wb.groesse());
        wb.streiche(eintrag);
        System.out.println(wb.groesse());

        Collection<Eintrag<Integer,String>> result = wb.findeAlle("lol");
        for(Eintrag<Integer,String> e : result){
            System.out.println(e.getW());
        }

        System.out.println(wb.finde("how").getW());
        */

    }

}
