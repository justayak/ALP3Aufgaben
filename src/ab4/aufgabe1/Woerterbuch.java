package ab4.aufgabe1;

import common.Eintrag;
import common.Helper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

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
    private static final int MAX_HASH_SIZE = 97;

    private LinkedList<Eintrag<S,T>>[] data;
    private int count = 0;

    /**
     * ctor
     */
    public Woerterbuch(){
        this.data = new LinkedList[MAX_HASH_SIZE];
    }

    @Override
    public Eintrag<S,T> finde(Object schluessel) {
        S key = (S) schluessel;
        int position = this.hash(key);
        if (this.data[position] != null){
            for(Eintrag<S,T> eintrag : this.data[position]){
                if (eintrag.getS().equals((S) schluessel)){
                    return eintrag;
                }
            }
        }
        return null;
    }

    @Override
    public void einfuege(Eintrag e) {
        Eintrag<S,T> eintrag = e; // X___X
        int position = this.hash(eintrag.getS());
        if (this.data[position] == null){
            this.data[position] = new LinkedList<Eintrag<S, T>>();
        }
        this.data[position].addLast(eintrag);
        this.count += 1;
    }

    @Override
    public void streiche(Eintrag e) {  // warum muss ich einen ganzen Eintrag übergeben? Der Schlüssel reicht doch?
        int position = this.hash(((Eintrag<S,T>)e).getS());
        if (this.data[position] != null){
            int i = 0;
            for(Eintrag<S,T> eintrag : this.data[position]){
                if (eintrag.getS().equals(((Eintrag<S,T>)e).getS())){
                    this.data[position].remove(i);
                    this.count -= 1;
                    break;
                }
                i++;
            }
        }
    }

    @Override
    public int groesse() {
        return this.count;
    }

    @Override
    public boolean istLeer() {
        return this.count == 0;
    }

    @Override
    public Collection findeAlle(Object o) {
        S key = (S) o;
        int position = this.hash(key);
        return this.data[position];
    }

    /**
     * generates a hash from the given key
     * @param key
     * @return
     */
    private int hash(S key){
        final int prime = 5;
        byte[] bytes = Helper.serialize(key);
        int hash = 0;
        for(int i = 0; i < bytes.length; i++){
            hash += bytes[i] * prime;
        }
        hash = (hash % (MAX_HASH_SIZE - 1)); // Modulo um das Ergebnis auf die richtige Größe einzudampfen
        return Math.abs(hash); // Java macht komische Sachen mit negativem Modulo..
    }
}
