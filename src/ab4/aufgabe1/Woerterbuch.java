package ab4.aufgabe1;

import common.Eintrag;
import common.Helper;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 19.11.12
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
public class Woerterbuch<S,T> implements IWoerterBuch{

    /**
     * defines the maximum size of the Wörterbuch
     */
    private static final int MAX_HASH_SIZE = 100;

    private LinkedList<Eintrag<S,T>>[] data;
    /**
     * ctor
     */
    public Woerterbuch(){
        this.data = new LinkedList[MAX_HASH_SIZE];
    }

    @Override
    public Eintrag<S,T> finde(Object schluessel) {
        return null;
    }

    @Override
    public void einfuege(Eintrag e) {

    }

    @Override
    public void streiche(Eintrag e) {

    }

    @Override
    public int groesse() {
        return 0;
    }

    @Override
    public boolean istLeer() {
        return false;
    }

    @Override
    public Collection findeAlle(Object o) {
        return null;
    }

    /**
     * generates a hash from the given key
     * @param key
     * @return
     */
    private int hash(S key){
        byte[] bytes = Helper.serialize(key);
        int hash = bytes.length;
        for(int i = 0; i < bytes.length;i++){
            if (i%2==0){
                hash -= bytes[i];
            }else{
                hash /= bytes[i];
            }
        }


        return hash;
    }
}
