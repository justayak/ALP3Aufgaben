package ab3.aufgabe1;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 11.11.12
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class Woerterbuch {

    /**
     * this is dirty but just used inside of this class [Woerterbuch]
     */
    private class Element implements Comparable<Element>{
        public int K;
        public String V;
        public Element(int k, String v){
            this.K = k;
            this.V = v;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.K, o.K);
        }
    }

    private int size;
    private Element[] elements;
    private int currentFreeField = 0;

    /**
     * ctor
     * @param size of the Wörterbuch. Must be positive
     */
    public Woerterbuch(int size){
        if (size <= 0){
            throw new RuntimeException("Wörterbuch darf nur positive Größen haben omg");
        }
        this.size = size;
        this.elements = new Element[size];
    }

    /**
     * @return the actual count of elements in the Wörterbuch
     */
    public int count(){
        return this.currentFreeField;
    }

    /**
     * @return an array of all keys
     */
    public int[] keys(){
        int[] result = new int[this.count()];
        for(int i = 0; i < result.length; i++){
            result[i] = this.elements[i].K;
        }
        return result;
    }

    /**
     * adds a Key-Value pair to the Wörterbuch
     * @param k like in 'K'ey
     * @param v like in 'V'alue
     */
    public void insert(int k, String v){
        if (this.currentFreeField == this.size){
            throw new RuntimeException("Noes! Kein Platz mehr im Wörterbuch");
        }
        // check if key is already in use..
        for(int i = 0; i < this.elements.length; i++){
            if (this.elements[i].K == k){
                throw new RuntimeException("Noes! Der Key ist schon benutzt..");
            }
        }
        this.elements[this.currentFreeField] = new Element(k,v);
        this.currentFreeField++;
    }

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    /**
     * gets the element with quicksort
     * @param k tes Element
     * @return
     */
    public String quickSelect(int k){
        switch (this.count()){
            case 0:
                throw new RuntimeException("Key not found (Wörterbuch ist leer)");
            case 1:
                if(this.elements[0].K == k){
                    return this.elements[0].V;
                }
                throw new RuntimeException("Key not found");
            default:
                int[] S = this.keys(); // should be optimized if the whole method is monitored for performance
                int a = this.getRandomKey(); // should be optimized if the whole method is monitored for performance




                return null;
        }
    }

    //private int[] lower(int[] basis, int )

    /**
     * @return a random Key from S
     */
    private int getRandomKey(){
        int[] S = this.keys();
        Random random = new Random();
        int pointer = random.nextInt(this.count());
        return S[pointer];
    }

}
