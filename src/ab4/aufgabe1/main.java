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


    }

}
