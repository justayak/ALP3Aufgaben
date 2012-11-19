package ab3.aufgabe2;

import common.Eintrag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 14.11.12
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main(String[]args) throws LeereWarteschlangeException{
        Prioritaetswarteschlange list = new ListPWS<Integer, String>();

        List<Eintrag<Integer, String>> all = new ArrayList<Eintrag<Integer, String>>();
        Eintrag<Integer, String> eintrag1 = new Eintrag<Integer, String>(10,"hallo"); all.add(eintrag1);
        Eintrag<Integer, String> eintrag2 = new Eintrag<Integer, String>(1,"dallo");  all.add(eintrag2);
        Eintrag<Integer, String> eintrag3 = new Eintrag<Integer, String>(76,"qallo"); all.add(eintrag3);
        Eintrag<Integer, String> eintrag4 = new Eintrag<Integer, String>(2,"qallo");  all.add(eintrag4);
        Eintrag<Integer, String> eintrag5 = new Eintrag<Integer, String>(3,"qallo");  all.add(eintrag5);
        Eintrag<Integer, String> eintrag6 = new Eintrag<Integer, String>(5,"qallo");  all.add(eintrag6);
        Eintrag<Integer, String> eintrag7 = new Eintrag<Integer, String>(1,"daasdasllo");  all.add(eintrag7);
        Eintrag<Integer, String> eintrag8 = new Eintrag<Integer, String>(1,"daladqwelo");  all.add(eintrag8);


        for(Eintrag<Integer, String> eintrag : all){
            list.einfuege(eintrag);
        }

        System.out.println(list);

        System.out.println(list.streicheMin());

        System.out.println(list);
    }

}
