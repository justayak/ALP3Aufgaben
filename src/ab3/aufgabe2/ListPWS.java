package ab3.aufgabe2;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 14.11.12
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class ListPWS<S extends Comparable<S>,W> implements Prioritaetswarteschlange  {

    private LinkedList<S> data;

    /**
     * ctor
     */
    public ListPWS(){
        this.data = new LinkedList<S>();
    }

    @Override
    public void einfuege(Eintrag eintrag) {   // dummerweise kann mein Compiler das nicht -> public void einfuege(Eintrag<S,W> eintrag) -> Der mault dann immer rum..
        Eintrag<S,W> e = eintrag;

    }

    public Eintrag<S,W> streicheMin() throws LeereWarteschlangeException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int groesse() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean istLeer() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Eintrag<S,W> min() throws LeereWarteschlangeException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
