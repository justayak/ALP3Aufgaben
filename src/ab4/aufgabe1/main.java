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
        Woerterbuch<Integer,String> wb = new Woerterbuch<Integer, String>();
        Eintrag<Integer, String> eintrag = new Eintrag<Integer, String>(1, "hallo");
        Eintrag<Integer, String> eintrag1 = new Eintrag<Integer, String>(3, "hallo1");
        Eintrag<Integer, String> eintrag2 = new Eintrag<Integer, String>(1, "hallo2");
        wb.einfuege(eintrag);
        wb.einfuege(eintrag1);
        wb.einfuege(eintrag2);
        System.out.println(wb.groesse());
        wb.streiche(eintrag);
        System.out.println(wb.groesse());

        Collection<Eintrag<Integer,String>> result = wb.findeAlle(1);
        for(Eintrag<Integer,String> e : result){
            System.out.println(e.getW());
        }

        System.out.println(wb.finde(3).getW());


    }

}
