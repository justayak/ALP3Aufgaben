package ab3.aufgabe2;

import common.Eintrag;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 14.11.12
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class ListPWS<S extends Comparable<S>,W> implements Prioritaetswarteschlange  {

    private LinkedList<Eintrag<S,W>> data;

    /**
     * ctor
     */
    public ListPWS(){
        this.data = new LinkedList<Eintrag<S,W>>();
    }

    @Override
    public void einfuege(Eintrag eintrag) {   // dummerweise kann mein Compiler das nicht -> public void einfuege(Eintrag<S,W> eintrag) -> Der mault dann immer rum..
        if (this.istLeer()){
            this.data.add(eintrag);
        }else {
            int i = this.insertStep(eintrag,this.data.listIterator(0),0);
            if (i >= 0){
                this.data.add(i,eintrag);
            }else {
                this.data.addLast(eintrag);
            }
        }
    }

    private int insertStep(Eintrag<S,W> e, ListIterator itr, int acc){
        if (itr.hasNext()){
            Eintrag<S,W> b = (Eintrag<S,W>)itr.next();
            int comp = e.getS().compareTo(b.getS());
            if (comp >= 0){
                acc++;
                return insertStep(e, itr,acc);
            }else {
                return acc;
            }
        }else {
            return -1;
        }

    }

    public Eintrag<S,W> streicheMin() throws LeereWarteschlangeException {
        if(this.groesse() <= 0){
            throw new LeereWarteschlangeException();
        }
        Eintrag<S,W> result = this.data.getFirst();
        this.data.removeFirst();
        return result;
    }

    public int groesse() {
        return this.data.size();
    }

    public boolean istLeer() {
        return this.data.isEmpty();
    }

    public Eintrag<S,W> min() throws LeereWarteschlangeException {
        if(this.groesse() <= 0){
            throw new LeereWarteschlangeException();
        }
        return this.data.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.data.size(); i++){
            builder.append("i:");
            builder.append(i);
            builder.append("{}:");
            builder.append(this.data.get(i));
            builder.append("\n\r"); // im using windows..
        }
        return builder.toString();
    }
}
