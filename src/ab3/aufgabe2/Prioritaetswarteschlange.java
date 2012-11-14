package ab3.aufgabe2;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 14.11.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public interface Prioritaetswarteschlange<S extends Comparable<S>,W> {
    // wichtige Operationen
    public void einfuege(Eintrag<S,W> eintrag);
    public Eintrag<S,W> streicheMin() throws LeereWarteschlangeException;

    // weitere nÃ¼tzliche Operationen
    public int groesse();
    public boolean istLeer();
    public Eintrag<S,W> min() throws LeereWarteschlangeException;
}
