package ab4.aufgabe1;

import common.Eintrag;

import java.util.Collection;

public interface IWoerterBuch<S,W>{

    //wichtige Operationen
    public Eintrag<S,W> finde(S schluessel);
    public void einfuege (Eintrag<S,W> e);
    public void streiche (Eintrag<S,W> e);

    // weitere nÃ¼tzliche Operationen
    public int groesse();
    public boolean istLeer();

    //kÃ¶nnte man auch noch aufnehmen

    public Collection<Eintrag<S,W>> findeAlle(S s);

}
