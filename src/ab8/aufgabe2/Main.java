package ab8.aufgabe2;

import common.Helper;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Baka
 * Date: 16.12.12
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Text gewichten..
        String text = Helper.loadText("EinWinterMaerchen");
        Tokens tokens = new Tokens();
        for(int i = 0; i < text.length(); i++){
            tokens.addToken(text.substring(i,i+1));
        }

        // Huffman-Tree bauen..
        PriorityQueue<Node> leafs = tokens.getLeafs();
        while (leafs.size()>1){
            Node left = leafs.poll();
            Node right = leafs.poll();
            Node parent = new Node(left,right);
            leafs.add(parent);
        };
        Node huffmanTree = leafs.poll();

        System.out.print("q");
    }


    /**
     *
     */
    private static class Node implements Comparable<Node> {
        /**
         * ctor for Leafs
         */
        public Node(String token, int weight){
            this.Token = token;
            this.Weight = weight;
        }

        /**
         * ctor for inner node (or root)
         */
        public Node(Node left, Node right){
            this.Left = left;
            this.Right = right;
            left.parent = this;
            right.parent = this;
            this.Weight = left.Weight + right.Weight;
        }

        public int Weight;
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
        public PriorityQueue<Node> getLeafs(){
            PriorityQueue<Node> result = new PriorityQueue<Node>();
            Iterator it = this.data.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String, Integer> pair = (Map.Entry)it.next();
                result.add(new Node(pair.getKey(),pair.getValue()));
            }
            return result;
        }
    }

}
