package ab8.aufgabe2;

import common.Helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 16.12.12
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Text gewichten:
        String text = Helper.loadText("EinWinterMaerchen");
        Tokens tokens = new Tokens();
        for(int i = 0; i < text.length(); i++){
            tokens.addToken(text.substring(i,i+1));
        }

        tokens.getLeafs();
    }


    private static class HuffmanGraph{



    }

    /**
     *
     */
    private static class Node implements Comparable<Node> {
        /**
         * ctor for Leafs
         */
        public Node(String token, int weight){
            this.IsLeaf = true;
            this.Token = token;
            this.Weight = weight;
        }

        /**
         * ctor for inner node (or root)
         */
        public Node(Node left, Node right){
            this.IsLeaf = false;
            this.Left = left;
            this.Right = right;
            left.parent = this;
            right.parent = this;
            this.Weight = left.Weight + right.Weight;
        }

        public int Weight;
        public boolean IsLeaf;
        public String Token;
        public Node Left;
        public Node Right;
        public Node parent;
        @Override
        public int compareTo(Node o) {
            return ((Integer)this.Weight).compareTo(o.Weight);
        }
    }

    /**
     *
     */
    private static class Tokens {
        private HashMap<String, Integer> data = new HashMap<String, Integer>();
        public void addToken(String t){
            int value = 0;
            if(this.data.containsKey(t)){
                value = this.data.get(t);
            }
            this.data.put(t, value + 1);
        }
        public void getLeafs(){
            Iterator it = this.data.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String, Integer> pair = (Map.Entry)it.next();
                System.out.println(">" + pair.getKey() + " -- " + pair.getValue());
            }
        }
    }

}
